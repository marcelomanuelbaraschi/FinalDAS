package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import endpoint.CadenaEndpoint;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/cadenaRestOne")
public class CadenaRestOne  {

    private CadenaEndpoint ce = new CadenaEndpoint();
    private Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {

        //TODO LOG
        //System.out.println("Rest health identificador ->" + identificador);

        return "OK";
    }

    @GET
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    public String sucursales (@QueryParam("identificador") final String identificador
                        ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                        ,@QueryParam("localidad") final String localidad)
    {
        return ce.sucursales(codigoEntidadFederal,localidad);
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public String infoSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("idsucursal") final Long idSucursal)
    {
        //TODO log
        return ce.infoSucursales(idSucursal);
    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String preciosSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos)
    {
        //TODO log
       // System.out.println("Rest precios identificador ->" + identificador);
        return ce.preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }
}

