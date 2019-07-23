package ws;
import db.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Comparador.Comparador;
import utilities.ListUtils;
import utilities.ServiceHealth;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static javax.ws.rs.core.Response.status;
import static service.Cadenas.Cadenas.*;
import static service.CanastaBasica.CanastaBasica.*;
import static service.Ubicacion.Ubicacion.obtenerLocalidades;
import static service.Ubicacion.Ubicacion.obtenerProvincias;
import static service.MenuSaludable.MenuSaludable.armarPlato;
import static service.MenuSaludable.MenuSaludable.obtenerMenuSemanal;
import static utilities.GSON.toJson;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class WebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);
    private static final long timeOut = 30;

    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
        try{
            response.resume(toJson(obtenerCategorias()));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response
                               ,@QueryParam("idcategoria") final Short idcategoria
                               ,@QueryParam("marcas") final String marcas
                               ,@QueryParam("palabraclave") final String palabraclave)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
        criterio.setIdCategoria(idcategoria);
        criterio.setMarcas(marcas!=null?ListUtils.asList(marcas):null);
        criterio.setPalabraclave(palabraclave);

        try{
            List<Producto> productos = buscarProductos(criterio);

            response.resume(toJson(productos));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }


    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            response.resume(toJson(obtenerProvincias()));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            response.resume(toJson(obtenerLocalidades()));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
        try{
            response.resume(toJson(obtenerCadenas()));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            List<Configuracion> configuraciones = obtenerConfiguraciones();

            logger.error(configuraciones.toString());


            List<Cadena> infosuc = obtenerSucursales(codigoentidadfederal,localidad,configuraciones);

            response.resume(toJson(infosuc));

        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            System.out.println(1);
            List<Configuracion> configuraciones = obtenerConfiguraciones();
            System.out.println(2);

            List<Cadena> cadenas = obtenerPrecios(codigoentidadfederal, localidad, codigos, configuraciones);
            System.out.println(3);
            List<Producto> productosDelCarrito = buscarProductosPorCodigos(codigos);
            System.out.println(4);
            //separar
            Comparador comparador = new Comparador(cadenas, productosDelCarrito);
            System.out.println(5);

            comparador.comparar();

            System.out.println(6);
            List<Cadena> comparadas = comparador.obtenerComparacion();
            System.out.println(7);
            response.resume(toJson(comparadas));
            System.out.println(8);
        }catch(Exception exception){
            logger.error("Endpoint Failuree, {}",exception.getMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
        try{
            response.resume(toJson(obtenerMenuSemanal()));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                            ,@QueryParam("idplato") final short idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            response.resume(toJson(armarPlato(codigoentidadfederal,localidad,idplato)));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/traversalHealth")
    public void traversalHealth (@Suspended final AsyncResponse response)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            List<String> ls = ServiceHealth.traversalHealth();
            logger.debug( "entro" );
            response.resume(ls.toString());

        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

}
