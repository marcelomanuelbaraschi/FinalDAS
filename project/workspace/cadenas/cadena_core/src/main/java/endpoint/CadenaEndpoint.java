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

public class CadenaEndpoint {

    public CadenaEndpoint() {   }

    private Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();



    public String sucursales (final String codigoentidadfederal,
                              final String localidad)
    {
        if(codigoentidadfederal == null){

            Response resp = new Response();
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (gson.toJson(resp));

        }else if(localidad == null){

            Response resp = new Response();
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (gson.toJson(resp));

        } else{
            //TODO log
            try {
                CriterioLocalizacionSucursalBean loc = new CriterioLocalizacionSucursalBean();
                loc.setCodigoEntidadFederal(codigoentidadfederal);
                loc.setLocalidad(localidad);
                Dao dao = DaoFactory.getDao("Sucursales", "");
                List<Bean> locs = dao.select(loc);

                Response resp = new Response();
                resp.setCodigo(0);
                resp.setJson(gson.toJson(locs));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                //System.out.println("Error: " + ex.getMessage());
                Response resp = new Response();
                resp.setCodigo(1);
                resp.setMensaje("db error");
                return (gson.toJson(resp));
            }

        }

    }

    public String infoSucursales (final Long idsucursal)
    {
        if(idsucursal == null) {
            Response resp = new Response();
            resp.setCodigo(1);
            resp.setMensaje("El parametro idsucursal es null");
            return (gson.toJson(resp));
        }else{
            try {
                CriterioInfoSucursalBean loc = new CriterioInfoSucursalBean();
                loc.setIdSucursal(idsucursal);
                Dao dao = DaoFactory.getDao("InfoSucursal", "");
                List<Bean> info = dao.select(loc);

                Response resp = new Response();
                resp.setCodigo(0);
                resp.setJson(gson.toJson(info));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                //TODO log
                System.out.println("Error: " + ex.getMessage());
                Response resp = new Response();
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
        if(codigoentidadfederal == null){

            Response resp = new Response();
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (gson.toJson(resp));

        }else if (localidad ==  null){

            Response resp = new Response();
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (gson.toJson(resp));

        }else if (codigos == null){

            Response resp = new Response();
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

                Response resp = new Response();
                resp.setCodigo(0);
                resp.setMensaje("success info");
                resp.setJson(gson.toJson(ps));
                return (gson.toJson(resp));

            } catch (SQLException ex) {
                //TODO log
                System.out.println("Error: " + ex.getMessage());
                Response resp = new Response();
                resp.setCodigo(1);
                resp.setMensaje("db error");
                return (gson.toJson(resp));
            }
        }

    }
}
