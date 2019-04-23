package ws;

import endpoint.CadenaEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/cadenaRestOne")
public class CadenaRestOne  {

    protected static final Logger log = LoggerFactory.getLogger(CadenaRestOne.class);

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {
        //log.debug("ok");
        return "OK";
    }

    @GET
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    public String sucursales (@QueryParam("identificador") final String identificador
                             ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                             ,@QueryParam("localidad") final String localidad)
    {
        return CadenaEndpoint.getInstance().sucursales(codigoEntidadFederal,localidad);
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public String infoSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("idsucursal") final Long idSucursal)
    {
        return CadenaEndpoint.getInstance().infoSucursales(idSucursal);
    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String preciosSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos)
    {
        return CadenaEndpoint.getInstance().preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }
}

