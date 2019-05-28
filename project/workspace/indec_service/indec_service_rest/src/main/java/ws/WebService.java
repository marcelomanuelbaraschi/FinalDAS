package ws;

import db.beans.Cadena;
import db.beans.CriterioBusquedaProducto;
import service.*;
import utilities.GSON;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
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
    public void productos(@Suspended final AsyncResponse response
                         ,@QueryParam("idcategoria") final Short idcategoria
                         ,@QueryParam("keyword") final String keyword)
    {
        response.setTimeout(3600, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setKeyword(keyword);

        FutureOps.within(3600,SECONDS, executor ,supplyAsync(()->
                CanastaBasica.obtenerProductos(criterio))
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
                Localizacion.obtenerProvincias())
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
                Localizacion.obtenerLocalidades())
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

        response.setTimeout(6, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(6, SECONDS,executor,supplyAsync(() ->
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

        response.setTimeout(15, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(15, SECONDS,executor,supplyAsync(() ->
                Cadenas.obtenerConfiguraciones())
        )
        .thenApply((configuraciones) ->{
                final List<Cadena> cadenas =
                        Cadenas.obtenerPrecios(codigoentidadfederal,localidad,codigos,configuraciones);
                return (new Comparador()).compararPrecios(cadenas,codigos);
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

        response.setTimeout(3600, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(3600,SECONDS,executor,supplyAsync(() ->
                MenuSaludable.obtenerMenuSemanal())
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
//---------------------------Experimental---------------------
    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                             ,@QueryParam("idplato") final Integer idplato
                             ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                             ,@QueryParam("localidad") final String localidad) {

        response.setTimeout(6, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build())
        );

        FutureOps.within(6,SECONDS,executor,supplyAsync(() ->
                MenuSaludable.armarPlato(codigoentidadfederal,localidad,idplato))
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
