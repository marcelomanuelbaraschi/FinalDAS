package ws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IndecServiceException;
import service.IndecServiceImpl;
import utilities.JsonMarshaller;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class IndecRest {

    protected static final Logger logger = LoggerFactory.getLogger(IndecRest.class);

    @GET
    @Path("/categorias")
    public Response categorias () {
        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceImpl.getInstance().categorias())
            ).build();
        }
        catch (IndecServiceException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) .build();
        }
    }

    @GET
    @Path("/productos")
    public Response productos (@QueryParam("idcategoria") final Long idCategoria) {
        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceImpl.getInstance().productos(idCategoria))
            ).build();
        }
        catch (IndecServiceException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/provincias")
    public Response provincias () {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecServiceImpl.getInstance().provincias())
            ).build();
        }
        catch (IndecServiceException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/localidades")
    public Response localidades (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal) {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecServiceImpl.getInstance().localidades(codigoEntidadFederal))
            ).build();
        }
        catch (IndecServiceException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/cadenas")
    public Response cadenas () {
        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceImpl.getInstance().cadenas())
            ).build();
        }
        catch (IndecServiceException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/comparador")
    public Response comparador (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad
                               ,@QueryParam("codigos") final String codigos) {

     //TODO validar parametros
     //TODO agregar como parametro el criterio con el cual funcionara el comparador.
        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceImpl.getInstance().compararPrecios(codigoentidadfederal,localidad,codigos))
            ).build();
        }
        catch (IndecServiceException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/sucursales")
    public Response sucursales (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad) {

        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceImpl.getInstance().sucursales(codigoentidadfederal,localidad))
                    ).build();
        }
        catch (IndecServiceException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/health")
    public String health() {
        logger.debug("Healthy");
        return "OK";
    }

}

