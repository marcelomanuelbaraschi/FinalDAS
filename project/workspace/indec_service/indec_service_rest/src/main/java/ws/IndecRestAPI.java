package ws;
import beans.common_models.Cadena;
import utilities.GSON;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import static api.IndecAPI.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
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

    private static final ScheduledExecutorService executor =
            Executors.newScheduledThreadPool(3);

    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        within(3, SECONDS
        ,supplyAsync(() -> obtenerCategorias()))
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
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

        within(3,SECONDS
        ,supplyAsync(() -> obtenerProductos(idCategoria)))
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
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

        within(3,SECONDS
        ,supplyAsync(() -> obtenerProvincias()))
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
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

        within(3,SECONDS
        ,supplyAsync(() -> obtenerLocalidades(codigoEntidadFederal)))
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
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

        within(3,SECONDS
        ,supplyAsync(() -> obtenerCadenas()))
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
        });

    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
            , @QueryParam("codigoentidadfederal") final String codigoentidadfederal
            , @QueryParam("localidad") final String localidad){

        response.setTimeout(4, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        CompletableFuture<List<Cadena>> obtenerSucursalesPorCadenaConfigurada =
            supplyAsync(() -> { return
            obtenerConfiguraciones()
                    .parallelStream()
                    .map((config) -> obtenerSucursales(codigoentidadfederal, localidad, config))
                    .collect(toList());
            });

        within(3,SECONDS
        ,obtenerSucursalesPorCadenaConfigurada)
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
        });


    }

    //----------------------Private Methods----------------------------
    private static <T> CompletableFuture<T> within
            (long duration, TimeUnit unit, CompletableFuture<T> future)
    {
        final CompletableFuture<T> timeout = failAfter(duration, unit);
        return future.applyToEither(timeout, Function.identity());
    }

    private static <T> CompletableFuture<T> failAfter
            (long duration, TimeUnit unit)
    {
        final CompletableFuture<T> future = new CompletableFuture<>();
        executor.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return future.completeExceptionally(ex);
        }, duration, unit);
        return future;
    }

}
