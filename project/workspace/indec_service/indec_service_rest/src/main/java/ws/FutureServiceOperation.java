package ws;
import org.slf4j.Logger;
import service.CanastaBasica;
import utilities.GSON;

import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.*;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;
public class FutureServiceOperation {
    public static <T> CompletableFuture<T>
        within(long duration, TimeUnit unit, ScheduledExecutorService executor, CompletableFuture<T> future)
    {
        final CompletableFuture<T> timeout = failAfter(duration, unit, executor);
        return future.applyToEither(timeout, Function.identity());
    }

    private static  <T> CompletableFuture<T>
        failAfter(long duration, TimeUnit unit, ScheduledExecutorService executor)
    {
        final CompletableFuture<T> future = new CompletableFuture<>();
        executor.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return future.completeExceptionally(ex);
        }, duration, unit);
        return future;
    }


    public static void  execute (int i, TimeUnit seconds, Logger logger, AsyncResponse asyncResponse, CompletableFuture<String> futurejson) {

        asyncResponse.setTimeout(i, seconds);
        asyncResponse.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );
        futurejson.thenApply((json) -> asyncResponse.resume(json))
                  .exceptionally(exception -> {
                    logger.error("Endpoint Failure: {}", exception.getMessage());
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR)
                            .entity(exception)
                            .build());
        });
    }

    public static void runAsync(int n, TimeUnit timeUnit,Logger logger,AsyncResponse asyncResponse, CompletableFuture<String> futurejson){
        asyncResponse.setTimeout(n, timeUnit);
        asyncResponse.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        futurejson
        .thenApply((json) -> asyncResponse.resume(json))
        .exceptionally(exception -> {
                    logger.error("Endpoint Failure: {}", exception.getMessage());
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR)
                            .entity(exception)
                            .build());
        });
    }

}
