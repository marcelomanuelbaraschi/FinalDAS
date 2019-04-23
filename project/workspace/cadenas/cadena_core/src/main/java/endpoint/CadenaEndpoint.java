package endpoint;
import bean.CriterioBusquedaProductosBean;
import bean.CriterioInfoSucursalBean;
import bean.CriterioLocalizacionSucursalBean;
import bean.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CadenaEndpoint {

    private Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(CadenaEndpoint.class);
    private static volatile CadenaEndpoint endpoint;

    private CadenaEndpoint(){

        gson = (new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create());

    }

    public static CadenaEndpoint getInstance(){
        if (endpoint == null){
            endpoint = new CadenaEndpoint();
        }

        return endpoint;
    }

    public String sucursales (final String codigoentidadfederal,
                              final String localidad)
    {
        logger.debug("{} method called..","sucursales");
       Response resp = new Response();

        if(codigoentidadfederal == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (gson.toJson(resp));

        }else if(localidad == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (gson.toJson(resp));

        } else{
            try {
                CriterioLocalizacionSucursalBean loc = new CriterioLocalizacionSucursalBean();
                loc.setCodigoEntidadFederal(codigoentidadfederal);
                loc.setLocalidad(localidad);
                Dao dao = DaoFactory.getDao("Sucursales", "");
                List<Bean> locs = dao.select(loc);
                resp.setCodigo(0);
                resp.setJson(gson.toJson(locs));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                resp.setCodigo(1);
                resp.setMensaje("db error");
                return (gson.toJson(resp));
            }

        }
    }

    public String infoSucursales (final Long idsucursal)
    {
        logger.debug("{} method called..,","infoSucursales");
       Response resp = new Response();
        if(idsucursal == null) {
            resp.setCodigo(1);
            resp.setMensaje("El parametro idsucursal es null");
            return (gson.toJson(resp));
        }else{
            try {
                CriterioInfoSucursalBean loc = new CriterioInfoSucursalBean();
                loc.setIdSucursal(idsucursal);
                Dao dao = DaoFactory.getDao("InfoSucursal", "");
                List<Bean> info = dao.select(loc);
                resp.setCodigo(0);
                resp.setJson(gson.toJson(info));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
                resp.setCodigo(1);
                resp.setMensaje("db error");
                return (gson.toJson(resp));
            }
        }

    }

    public String preciosSucursales (final String codigoentidadfederal
                                    ,final String localidad
                                    ,final String codigos)
    {
        logger.debug("{} method called..","preciosSucursales");
        Response resp = new Response();
        if(codigoentidadfederal == null){

            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (gson.toJson(resp));

        }else if (localidad ==  null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (gson.toJson(resp));

        }else if (codigos == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigos es null");
            return (gson.toJson(resp));

        }
        else{
            try {
                CriterioBusquedaProductosBean cs = new CriterioBusquedaProductosBean();
                cs.setCodigoEntidadFederal(codigoentidadfederal);
                cs.setLocalidad(localidad);
                cs.setCodigos(codigos);
                Dao dao = DaoFactory.getDao("PreciosSucursales", "");
                List<Bean> ps = dao.select(cs);
                resp.setCodigo(0);
                resp.setJson(gson.toJson(ps));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                resp.setCodigo(1);
                resp.setMensaje("db error:" + ex);
                return (gson.toJson(resp));
            }
        }
    }
}
