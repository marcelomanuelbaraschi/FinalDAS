package ws;

import db.beans.Cadena;
import db.beans.CriterioBusquedaProducto;
import service.*;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static service.Cadenas.*;
import static service.Cadenas.obtenerCadenas;
import static service.CanastaBasica.*;
import static service.CanastaBasica.obtenerProductos;
import static service.Localizacion.*;
import static service.MenuSaludable.*;
import static utilities.GSON.toJson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/app")
@Produces(MediaType.TEXT_PLAIN)
public class WebService {


    private static final Logger logger =
            LoggerFactory.getLogger(WebService.class);


    private static final ExecutorService executor = ServiceOperation.newCustomCachedThreadPool();
    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse asyncResponse)
    {

        ServiceOperation.run(logger,asyncResponse,supplyAsync(() ->
                        toJson(obtenerCategorias())
                ,executor));
    }

    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse response,@QueryParam("idcategoria") final Short idcategoria,@QueryParam("marca") final String marca)
    {

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setMarca(marca);

        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerProductos(criterio))
                ,executor));

    }

    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response,
                                @QueryParam("palabraclave") final String palabraclave)
    {
        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(buscarProductos(palabraclave))
                ,executor));

    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response)
    {
        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerProvincias())
                ,executor));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response)
    {
        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerLocalidades())
                ,executor));
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response)
    {

        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerCadenas())
                ,executor));

    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad)
    {

        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerSucursales(codigoentidadfederal,localidad))
                ,executor));

    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos)
    {
        ServiceOperation.run(logger,response,supplyAsync(() -> {
                        final List<Cadena> cadenas = obtenerPrecios(codigoentidadfederal, localidad, codigos);
                        return toJson((new Comparador()).compararPrecios(cadenas, codigos));}
                ,executor));

    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response)
    {

        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(obtenerMenuSemanal())
                ,executor));

    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                            ,@QueryParam("idplato") final Integer idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad)
    {

        ServiceOperation.run(logger,response,supplyAsync(() ->
                        toJson(armarPlato(codigoentidadfederal,localidad,idplato))
                ,executor));

    }

}
