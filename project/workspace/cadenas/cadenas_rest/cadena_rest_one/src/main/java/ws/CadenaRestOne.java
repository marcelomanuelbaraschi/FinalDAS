package ws;

import api.CadenaAPI;
import bean.Sucursal;
import utilities.GSON;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cadenaRestOne")
@Produces(MediaType.APPLICATION_JSON)
public class CadenaRestOne  {

    @GET
    @Path("/health")
    public String health(){
        return "OK";
    }

    @GET
    @Path("/sucursales")
    public Response sucursales (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad)
    {
        try{
            final List<Sucursal> sucs =
                    CadenaAPI.sucursales(codigoentidadfederal, localidad);

            return Response.status(Response.Status.OK).entity(
                    GSON.toJson(sucs)
            ).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/precios")
    public Response preciosSucursales (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                                      ,@QueryParam("localidad") final String localidad
                                      ,@QueryParam("codigos") final String codigos)
    {
    
        try{
            final List<Sucursal> ps =
                    CadenaAPI.preciosSucursales(codigoEntidadFederal, localidad, codigos);

            return Response.status(Response.Status.OK).entity(
                    GSON.toJson(ps)
            ).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

