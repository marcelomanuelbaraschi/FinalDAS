package ws;
import db.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Comparador.Comparador;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import static java.util.concurrent.CompletableFuture.supplyAsync;


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

    @GET
    @Path("/categorias")
    public void categorias(@Suspended final AsyncResponse response)
    {
        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(obtenerCategorias())
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

        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(obtenerProductos(criterio))
        ));

    }

    @GET
    @Path("/buscarproductosxcodigos")
    public void buscarproductosxcodigos( @Suspended final AsyncResponse response
                                        ,@QueryParam("codigos") final String codigos)
    {

        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(buscarProductosPorCodigos(codigos))
        ));

    }


    @GET
    @Path("/buscarproductos")
    public void buscarproductos(@Suspended final AsyncResponse response
                               ,@QueryParam("palabraclave") final String palabraclave)
    {

        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(buscarProductos(palabraclave))
        ));

    }

    @GET
    @Path("/provincias")
    public void provincias(@Suspended final AsyncResponse response)
    {

        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(obtenerProvincias())
        ));
    }

    @GET
    @Path("/localidades")
    public void localidades(@Suspended final AsyncResponse response)
    {
        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(obtenerLocalidades())
        ));

    }

    @GET
    @Path("/cadenas")
    public void cadenas(@Suspended final AsyncResponse response)
    {

        ServiceOperation.run(logger,response,supplyAsync(
                () -> toJson(obtenerCadenas())
        ));

    }

    @GET
    @Path("/sucursales")
    public void sucursales(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad)
    {

        ServiceOperation.run(logger,response,supplyAsync(

                () -> {
                    List<Configuracion> configuraciones = obtenerConfiguraciones();

                    List<Cadena> infosuc = obtenerSucursales(codigoentidadfederal,localidad,configuraciones);

                    return toJson(infosuc);
                }
        ));
    }

    @POST
    @Path("/comparador")
    public void comparador(@Suspended final AsyncResponse response
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos) {

        ServiceOperation.run( logger, response, supplyAsync(
                () -> {
                        List<Configuracion> configuraciones = obtenerConfiguraciones();

                        List<Cadena> cadenas = obtenerPrecios(codigoentidadfederal, localidad, codigos, configuraciones);

                        List<Producto> productos = buscarProductosPorCodigos( codigos );

                        List<Cadena> sucursalesComparadas = (new Comparador()).compararPrecios(cadenas, productos);

                        return toJson( sucursalesComparadas);
                    }
                ));
    }

    @GET
    @Path("/menu")
    public void menu (@Suspended final AsyncResponse response)
    {

        ServiceOperation.run( logger, response, supplyAsync(
                () -> toJson(obtenerMenuSemanal())
        ));
    }

    @GET
    @Path("/comparadorplato")
    public void armarplato (@Suspended final AsyncResponse response
                            ,@QueryParam("idplato") final short idplato
                            ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                            ,@QueryParam("localidad") final String localidad)
    {
        ServiceOperation.run( logger, response, supplyAsync(
                () -> {
                    List<Cadena> cadenas = armarPlato(codigoentidadfederal,localidad,idplato);
                    return toJson(cadenas);
                }
        ));
    }

}
