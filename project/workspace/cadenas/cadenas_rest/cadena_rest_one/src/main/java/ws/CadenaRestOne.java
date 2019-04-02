package ws;

import bean.CriterioInfoBean;
import bean.CriterioLocalizacionBean;
import bean.CriterioSeleccionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/cadenaRestOne")
public class CadenaRestOne  {

    private Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {

        System.out.println("Rest health identificador ->" + identificador);

        return "OK";
    }

    @GET
    @Path("/sucursales")
    @Produces(MediaType.APPLICATION_JSON)
    public String sucursales (@QueryParam("identificador") final String identificador
                        ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
                        ,@QueryParam("localidad") final String localidad)
    {
        System.out.println("Rest sucursales identificador ->" + identificador);
        try {
            CriterioLocalizacionBean loc = new CriterioLocalizacionBean();
            loc.setCodigoEntidadFederal(codigoEntidadFederal);
            loc.setLocalidad(localidad);
            Dao dao = DaoFactory.getDao("Sucursales", "");
            List<Bean> locs = dao.select(loc);
            return (gson.toJson(locs));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "[]";
        }


    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public String infoSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("idsucursal") final Long idSucursal)
    {
        System.out.println("Rest info identificador ->" + identificador);
        try {
            CriterioInfoBean loc = new CriterioInfoBean();
            loc.setIdSucursal(idSucursal);
            Dao dao = DaoFactory.getDao("InfoSucursal", "");
            List<Bean> locs = dao.select(loc);
            return (gson.toJson(locs));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "[]";
        }


    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String preciosSucursales (@QueryParam("identificador") final String identificador
            ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos)
    {
        System.out.println("Rest precios identificador ->" + identificador);
        System.out.println("Rest precios codigoEntidadFederal ->" + codigoEntidadFederal );
        System.out.println("Rest precios localidad ->" + localidad );
        System.out.println("Rest precios codigos ->" + codigos );
        try {
            CriterioSeleccionBean cs = new CriterioSeleccionBean();
            cs.setCodigoEntidadFederal(codigoEntidadFederal);
            cs.setLocalidad(localidad);
            cs.setCodigos(codigos);
            Dao dao = DaoFactory.getDao("PreciosSucursales", "");
            List<Bean> sucs = dao.select(cs);
            return (gson.toJson(sucs));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "[]";
        }


    }
}

