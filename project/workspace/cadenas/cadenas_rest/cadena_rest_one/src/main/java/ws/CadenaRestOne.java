package ws;

import bean.CriterioLocalizacionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
            return "must be a proper error";
        }


    }
}

