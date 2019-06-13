package ws;
import db.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Comparador.Comparador;
import utilities.ListUtils;

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
    private static final long timeOut = 3;

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
            response.resume(toJson(buscarProductos(criterio)));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }

    @GET
    @Path("/buscarproductosxcodigos")
    public void buscarproductosxcodigos( @Suspended final AsyncResponse response
                                        ,@QueryParam("codigos") final String codigos)
    {


        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            response.resume(toJson(buscarProductosPorCodigos(codigos)));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }

    }

   /* @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response
                               ,@QueryParam("palabraclave") final String palabraclave)
    {
        response.setTimeout(timeOut, SECONDS);
        response.setTimeoutHandler(
                (resp) -> resp.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));

        try{
            response.resume(toJson(buscarProductos(palabraclave)));
        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
            response.resume(status(INTERNAL_SERVER_ERROR)
                    .build());
        }
    }*/

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
            List<Configuracion> configuraciones = obtenerConfiguraciones();

            List<Cadena> cadenas = obtenerPrecios(codigoentidadfederal, localidad, codigos, configuraciones);

            List<Producto> productos = buscarProductosPorCodigos(codigos);

            List<Cadena> sucursalesComparadas = (new Comparador()).compararPrecios(cadenas, productos);

            response.resume(toJson( sucursalesComparadas)) ;

        }catch(Exception exception){
            logger.error("Endpoint Failure, {}",exception.getLocalizedMessage());
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
}
