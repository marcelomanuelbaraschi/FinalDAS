package api;
import bean.CriterioBusquedaProductosBean;
import bean.CriterioLocalizacionSucursalBean;
import bean.Response;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.JsonMarshaller;

public class CadenaApi {

    private static final Logger logger = LoggerFactory.getLogger(CadenaApi.class);
    private static volatile CadenaApi cadenaApiInstance;

    private CadenaApi(){ }

    public static CadenaApi getInstance(){
        if (cadenaApiInstance == null){
            cadenaApiInstance = new CadenaApi();
        }

        return cadenaApiInstance;
    }

    public String sucursales (final String codigoentidadfederal,
                              final String localidad)
            //TODO AGREGAR UN PARAMETRO OPCIONAL LIST(IDCADENAS) PARA TRAER SOLO DE ESAS CADENAS.
    {
        logger.debug("{} method called..","sucursales");
       Response resp = new Response();

        if(codigoentidadfederal == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (JsonMarshaller.toJson(resp));

        }else if(localidad == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (JsonMarshaller.toJson(resp));

        } else{
            try {
                CriterioLocalizacionSucursalBean loc = new CriterioLocalizacionSucursalBean();
                loc.setCodigoEntidadFederal(codigoentidadfederal);
                loc.setLocalidad(localidad);
                Dao dao = DaoFactory.getDao("Sucursales", "");
                List<Bean> locs = dao.select(loc);
                resp.setCodigo(0);
                resp.setJson(JsonMarshaller.toJson(locs));
                return (JsonMarshaller.toJson(resp));

            } catch (SQLException ex) {
                resp.setCodigo(1);
                resp.setMensaje("db error");
                return (JsonMarshaller.toJson(resp));
            }

        }
    }
/*
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

    }*/

    public String preciosSucursales (final String codigoentidadfederal
                                    ,final String localidad
                                    ,final String codigos)
    {
        logger.debug("{} method called..","preciosSucursales");
        Response resp = new Response();
        if(codigoentidadfederal == null){

            resp.setCodigo(1);
            resp.setMensaje("El parametro codigoentidadfederal es null");
            return (JsonMarshaller.toJson(resp));

        }else if (localidad ==  null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro localidad es null");
            return (JsonMarshaller.toJson(resp));

        }else if (codigos == null){
            resp.setCodigo(1);
            resp.setMensaje("El parametro codigos es null");
            return (JsonMarshaller.toJson(resp));

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
                resp.setJson(JsonMarshaller.toJson(ps));
                return (JsonMarshaller.toJson(resp));

            } catch (SQLException ex) {
                resp.setCodigo(1);
                resp.setMensaje("db error:" + ex);
                return (JsonMarshaller.toJson(resp));
            }
        }
    }
}
