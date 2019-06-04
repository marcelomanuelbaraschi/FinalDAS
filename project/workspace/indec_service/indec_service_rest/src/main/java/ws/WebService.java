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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static service.CanastaBasica.obtenerProductos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/app")
@Produces(MediaType.TEXT_PLAIN)
public class WebService {


    private static final Logger logger =
            LoggerFactory.getLogger(WebService.class);


    private static final ExecutorService executor = Executors.newWorkStealingPool();
    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse asyncResponse)
    {

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                 GSON.toJson(CanastaBasica.obtenerCategorias()),executor));
    }

    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse asyncResponse,@QueryParam("idcategoria") final Short idcategoria,@QueryParam("marca") final String marca)
    {

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setMarca(marca);

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                GSON.toJson(obtenerProductos(criterio)),executor));

    }

    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse asyncResponse,
                                @QueryParam("palabraclave") final String palabraclave)
    {
        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                GSON.toJson(CanastaBasica.buscarProductos(palabraclave)),executor));

    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse asyncResponse)
    {
        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                GSON.toJson(Localizacion.obtenerProvincias()),executor));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse asyncResponse)
    {
        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                        GSON.toJson(Localizacion.obtenerLocalidades())));
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse asyncResponse)
    {

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                        GSON.toJson(Cadenas.obtenerCadenas()),executor));

    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse asyncResponse
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad)
    {

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                        GSON.toJson(Cadenas.obtenerSucursales(codigoentidadfederal,localidad)),executor));

    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse asyncResponse
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos)
    {
         FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() -> {
                    final List<Cadena> cadenas = Cadenas.obtenerPrecios(codigoentidadfederal, localidad, codigos);
                    return GSON.toJson((new Comparador()).compararPrecios(cadenas, codigos));
                    },executor));

    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse asyncResponse)
    {

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                GSON.toJson(MenuSaludable.obtenerMenuSemanal()),executor));

    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse asyncResponse
                            ,@QueryParam("idplato") final Integer idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad)
    {

        FutureServiceOperation.runAsync(3,SECONDS,logger,asyncResponse,supplyAsync(() ->
                        GSON.toJson(MenuSaludable.armarPlato(codigoentidadfederal,localidad,idplato)),executor));

    }

}
