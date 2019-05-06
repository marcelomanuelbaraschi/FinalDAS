package ws;
import api.IndecAPI;
import utilities.GSON;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.*;
import java.util.function.Function;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static javax.ws.rs.core.Response.status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class IndecRestAPI {


    private static final Logger logger =
            LoggerFactory.getLogger(IndecRestAPI.class);

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(4);

    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                              .entity("Operation timed out")
                              .build())
        );

        within(3, SECONDS,
                supplyAsync(() -> IndecAPI.obtenerCategorias())
        )
        .thenApply(
                GSON::toJson
        )
        .thenApply(
                response::resume
        )
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                           .entity(exception)
                           .build());
        });
    }

    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse response
                         ,@QueryParam("idcategoria") final Long idCategoria) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                              .entity("Operation timed out")
                              .build())
        );

        within(3, SECONDS,
                supplyAsync(() -> IndecAPI.obtenerProductos(idCategoria))
        )
        .thenApply(
                GSON::toJson
        )
        .thenApply(
                response::resume
        )
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                           .entity(exception)
                           .build());
        });

    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                              .entity("Operation timed out")
                              .build())
        );

        within(3, SECONDS,
                supplyAsync(() -> IndecAPI.obtenerProvincias())
        )
        .thenApply(
                GSON::toJson
        )
        .thenApply(
                response::resume
        )
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
        });
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response
            , @QueryParam("codigoentidadfederal") final String codigoEntidadFederal) {


        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                              .entity("Operation timed out")
                              .build())
        );

        within(3, SECONDS,
                supplyAsync(() -> IndecAPI.obtenerLocalidades(codigoEntidadFederal))
        )
        .thenApply(
                GSON::toJson
        )
        .thenApply(
                response::resume
        )
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                           .entity(exception)
                           .build());
        });

    }


    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        within(3, SECONDS,
                supplyAsync(() -> IndecAPI.obtenerCadenas())
        )
        .thenApply(
                GSON::toJson
        )
        .thenApply(
                response::resume
        )
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                           .entity(exception)
                           .build());
        });

    }



    //----------------------Private Methods----------------------------
    private static <T> CompletableFuture<T> within
            (long duration, TimeUnit unit, CompletableFuture<T> future) {
        final CompletableFuture<T> timeout = failAfter(duration, unit);
        return future.applyToEither(timeout, Function.identity());
    }

    private static <T> CompletableFuture<T> failAfter(long duration, TimeUnit unit) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        scheduler.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return future.completeExceptionally(ex);
        }, duration, unit);
        return future;
    }
}
