package ws;

import org.slf4j.Logger;

import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static javax.ws.rs.core.Response.status;

public class RunOp {
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


    public static void configureTimer(int i, TimeUnit seconds, AsyncResponse response) {
        response.setTimeout(i, seconds);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

    }

    public static void runXinc(int i, TimeUnit seconds, ScheduledExecutorService executor,Logger logger, AsyncResponse response, CompletableFuture<String> future) {
         within(i,seconds,executor,future)
        .thenApply(response::resume)
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
        });
    }

}
