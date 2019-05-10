package ws;

import service.CadenaAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cadenaRestTwo")
public class CadenaRestTwo {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(){
        return "OK";
    }

    @GET
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    public String sucursales (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                             ,@QueryParam("localidad") final String localidad)
    {
        return CadenaAPI.getInstance()
                        .sucursales(codigoEntidadFederal,localidad);
    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String preciosSucursales (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                                    ,@QueryParam("localidad") final String localidad
                                    ,@QueryParam("codigos") final String codigos)
    {
        return CadenaAPI.getInstance()
                        .preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }
}
