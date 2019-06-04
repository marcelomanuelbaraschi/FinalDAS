package ws;

import db.Bean;
import db.beans.Cadena;
import db.beans.CriterioBusquedaProducto;
import service.*;
import utilities.GSON;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static javax.ws.rs.core.Response.status;
import static service.CanastaBasica.obtenerProductos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/app")
@Produces(MediaType.TEXT_PLAIN)
public class WebService {


    private static final Logger logger =
            LoggerFactory.getLogger(WebService.class);

           ExecutorService executor = Executors.newCachedThreadPool();

    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse response)
    {

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                GSON.toJson(CanastaBasica.obtenerCategorias())
        ,executor));
    }

    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse response,@QueryParam("idcategoria") final Short idcategoria,@QueryParam("marca") final String marca)
    {

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setMarca(marca);

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                GSON.toJson(obtenerProductos(criterio))
        ,executor));

    }

    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response,
                                @QueryParam("palabraclave") final String palabraclave)
    {
        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(CanastaBasica.buscarProductos(palabraclave))
        ,executor));
    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response)
    {
        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(Localizacion.obtenerProvincias())
        ,executor));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response)
    {
        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(Localizacion.obtenerLocalidades())
        ,executor));
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response)
    {

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(Cadenas.obtenerCadenas())
        ,executor));

    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad)
    {

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(Cadenas.obtenerSucursales(codigoentidadfederal,localidad))
        ,executor));

    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos)
    {
        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() -> {
                    final List<Cadena> cadenas = Cadenas.obtenerPrecios(codigoentidadfederal, localidad, codigos);
                    return GSON.toJson((new Comparador()).compararPrecios(cadenas, codigos));
                    }
        ,executor));

    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response)
    {

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                GSON.toJson(MenuSaludable.obtenerMenuSemanal())
        ,executor));

    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                            ,@QueryParam("idplato") final Integer idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad)
    {

        FutureOp.execute(3,SECONDS,logger,response,supplyAsync(() ->
                        GSON.toJson(MenuSaludable.armarPlato(codigoentidadfederal,localidad,idplato))
        ,executor));

    }

}
