package ws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.IndecRepository;
import repository.exceptions.RepositoryException;
import utilities.JsonMarshaller;
import comparador.IndecComparador;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/app")
public class IndecRest {

    protected static final Logger logger = LoggerFactory.getLogger(IndecRest.class);

    @GET
    @Path("/categorias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categorias () {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecRepository.getInstance()
                                                         .categorias())
            ).build();
        }
        catch (RepositoryException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/productos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response productos (@QueryParam("idcategoria") final Long idCategoria) {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecRepository.getInstance()
                                                         .productos(idCategoria))
            ).build();
        }
        catch (RepositoryException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/provincias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response provincias () {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecRepository.getInstance()
                                                         .provincias())
            ).build();
        }
        catch (RepositoryException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/localidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response localidades (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal) {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecRepository.getInstance()
                                                         .localidades(codigoEntidadFederal))
            ).build();
        }
        catch (RepositoryException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/cadenas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadenas () {
        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecRepository.getInstance()
                                                         .cadenas())
            ).build();
        }
        catch (RepositoryException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/comparador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comparador (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad
                               ,@QueryParam("codigos") final String codigos) {

     //TODO validar parametros
     //TODO agregar como parametro el criterio con el cual funcionara el comparador.
     //TODO utilizar el identificador

        try{
            return Response.status(Response.Status.OK).entity(
                    JsonMarshaller.toJson(IndecComparador.getInstance()
                                                         .compararPrecios(codigoentidadfederal,localidad,codigos))
            ).build();
        }
        catch (RepositoryException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }


    /* TODO : IMPLEMENTAR UN ENDPOINT "SUCURSALES" QUE PERMITA,DADO UNA LISTA DE CADENAS TRAER LA INFO DE LAS MISMAS*/

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health() {
        logger.debug("Healthy");
        return "OK";
    }

}

