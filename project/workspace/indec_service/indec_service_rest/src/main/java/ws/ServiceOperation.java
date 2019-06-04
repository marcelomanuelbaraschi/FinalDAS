package ws;
import org.slf4j.Logger;
import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;
public class ServiceOperation {

    private static final long timeToLive = 15L;
    private static final TimeUnit timeUnit = SECONDS;
    private static final int minimumPoolSize = 0;
    private static final int maximumPoolSize = 4;

    public static void run(Logger logger, AsyncResponse asyncResponse, CompletableFuture<String> futurejson){
        asyncResponse.setTimeout(timeToLive, timeUnit);
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

    public static ExecutorService newCustomCachedThreadPool() {
        return new ThreadPoolExecutor(minimumPoolSize, maximumPoolSize,
                timeToLive, timeUnit,
                new SynchronousQueue<Runnable>());
    }


}
