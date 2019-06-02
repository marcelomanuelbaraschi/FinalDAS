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

        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        CanastaBasica.obtenerCategorias()
                )
        ));
    }

    @GET
    @Path("/productos")
    public void productos(@Suspended final AsyncResponse response
                         ,@QueryParam("idcategoria") final Short idcategoria
                         ,@QueryParam("marca") final String marca)
    {

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setMarca(marca);

        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        CanastaBasica.obtenerProductos(criterio)
                )
        ));

    }

    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response,
                                @QueryParam("palabraclave") final String palabraclave)
    {
        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        CanastaBasica.buscarProductos(palabraclave)
                )
        ));
    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response)
    {
        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        Localizacion.obtenerProvincias()
                )
        ));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response) {
        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        Localizacion.obtenerLocalidades()
                )
        ));
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response) {
        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        Cadenas.obtenerCadenas()
                )
        ));
    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad) {

        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        Cadenas.obtenerSucursales(codigoentidadfederal,localidad)
                )
        ));

    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos)
    {
        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() -> {
            final List<Cadena> cadenas =  Cadenas.obtenerPrecios(codigoentidadfederal,localidad,codigos);
            return GSON.toJson((new Comparador()).compararPrecios(cadenas,codigos));
        }));

    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response) {

        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        MenuSaludable.obtenerMenuSemanal()
                )
        ));

    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                            ,@QueryParam("idplato") final Integer idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad) {

        FutureOp.execute(3,SECONDS,executor,logger,response,supplyAsync(() ->
                GSON.toJson(
                        MenuSaludable.armarPlato(codigoentidadfederal,localidad,idplato)
                )
        ));

    }

}
