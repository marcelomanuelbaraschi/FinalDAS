package ws;

import service.*;
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
public class WebService {


    private static final Logger logger =
            LoggerFactory.getLogger(WebService.class);

    private static final ScheduledExecutorService executor =
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

        FutureOps.within(3, SECONDS, executor, supplyAsync(()->
                CanastaBasica.obtenerCategorias())
        )
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
    public void productos(@Suspended final AsyncResponse response) {
        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(3,SECONDS, executor ,supplyAsync(()->
                CanastaBasica.obtenerProductos())
        )
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

        FutureOps.within(3,SECONDS,executor,supplyAsync(() ->
                Geo.obtenerProvincias())
        )
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
    public void localidades(@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(3,SECONDS,executor,supplyAsync(() ->
                Geo.obtenerLocalidades())
        )
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

        FutureOps.within(3,SECONDS,executor,supplyAsync(() ->
                Cadenas.obtenerCadenas())
        )
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
            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad) {

        response.setTimeout(50, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(50, SECONDS,executor,supplyAsync(() ->
                Cadenas.obtenerConfiguraciones())
        )
        .thenApply((configuraciones) ->
                Cadenas.obtenerSucursales(codigoentidadfederal,localidad,configuraciones)
        )
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
        });
}

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos)
    {

        response.setTimeout(50, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );
        Comparador comparador = new Comparador();
        FutureOps.within(50, SECONDS,executor,supplyAsync(() ->
                Cadenas.obtenerConfiguraciones())
        )
        .thenApply((configuraciones) ->
                Cadenas.obtenerSucursales(codigoentidadfederal,localidad,configuraciones)
        )
        .thenApply((cadenas) -> {
            return comparador.compararPrecios(cadenas);
        })
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
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response) {

        response.setTimeout(3, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(3,SECONDS,executor,supplyAsync(() ->
                Menu.obtenerMenuSemanal())
        )
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



/* @POST
    @Path("/armadorDePlato")
    public void armadorDePlato (@Suspended final AsyncResponse response
            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("idPlato") final Integer idPlato)
    {
        response.setTimeout(50, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );
        Comparador comparador = new Comparador();
        within(50,SECONDS,supplyAsync(() ->
                Menu.obtenerProductosPorPlato(idPlato))
        )
        .thenApply((codigos)->
        )
        .thenApply((cadenas) -> {
            return comparador.compararPrecios(cadenas);
        })
        .thenApply(GSON::toJson)
        .thenApply(response::resume)
        .exceptionally(exception -> {
            logger.error("Endpoint Failure: {}", exception.getMessage());
            return response.resume(status(INTERNAL_SERVER_ERROR)
                    .entity(exception)
                    .build());
            });

    }*/