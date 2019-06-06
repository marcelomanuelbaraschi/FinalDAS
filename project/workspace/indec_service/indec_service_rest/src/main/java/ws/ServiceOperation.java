package ws;
import org.slf4j.Logger;
import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;


public class ServiceOperation {

    private static final long timeOut = 20L;


    public static void run(Logger logger, AsyncResponse response,CompletableFuture<String> futurejson){

        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        futurejson
        .thenApply((json) -> {
                return response.resume(json);
        }).exceptionally( exception -> {
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        });
    }
}
