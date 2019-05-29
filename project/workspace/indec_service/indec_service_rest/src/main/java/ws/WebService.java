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

        RunOp.configureTimer(3, SECONDS,response);
        RunOp.runXinc(3,SECONDS,executor,logger,response,supplyAsync(() -> GSON.toJson(CanastaBasica.obtenerCategorias())));
    }


    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse response
                         ,@QueryParam("idcategoria") final Short idcategoria
                         ,@QueryParam("keyword") final String keyword)
    {
        RunOp.configureTimer(3, SECONDS,response);

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setKeyword(keyword);

        RunOp.runXinc(3,SECONDS,executor,logger,response,supplyAsync(() -> GSON.toJson(CanastaBasica.obtenerProductos(criterio))));

    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response) {
        RunOp.configureTimer(3, SECONDS,response);
        RunOp.runXinc(3,SECONDS,executor,logger,response,supplyAsync(() -> GSON.toJson(Localizacion.obtenerProvincias())));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response) {
        RunOp.configureTimer(3, SECONDS,response);
        RunOp.runXinc(3,SECONDS,executor,logger,response,supplyAsync(() -> GSON.toJson(Localizacion.obtenerLocalidades())));
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response) {
        RunOp.configureTimer(3, SECONDS,response);
        RunOp.runXinc(3,SECONDS,executor,logger,response,supplyAsync(() -> GSON.toJson(Cadenas.obtenerCadenas())));
    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad) {

        RunOp.configureTimer(6, SECONDS,response);
        RunOp.within(6, SECONDS,executor,supplyAsync(() ->
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

        RunOp.configureTimer(6, SECONDS,response);
        RunOp.within(6, SECONDS,executor,supplyAsync(() ->
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

        RunOp.configureTimer(3, SECONDS,response);
        RunOp.within(3,SECONDS,executor,supplyAsync(() ->
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

        RunOp.configureTimer(6, SECONDS,response);

        RunOp.within(6,SECONDS,executor,supplyAsync(() ->
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



}
