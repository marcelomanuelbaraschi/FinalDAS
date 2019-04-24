package ws;

import api.CadenaApi;

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
        return CadenaApi.getInstance()
                        .sucursales(codigoEntidadFederal,localidad);
    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String preciosSucursales (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                                    ,@QueryParam("localidad") final String localidad
                                    ,@QueryParam("codigos") final String codigos)
    {
        return CadenaApi.getInstance()
                        .preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }
}
