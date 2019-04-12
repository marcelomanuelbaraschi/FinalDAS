package ws;

import bean.CriterioLocalizacionSucursalBean;
import bean.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import endpoint.CadenaEndpoint;

import java.sql.SQLException;
import java.util.List;

public class CadenaAxisOne {

    private CadenaEndpoint ce = new CadenaEndpoint();
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    public String health(final String identificador) {
        //TODO LOG
        //System.out.println("Axis health identificador -> " + identificador);
        return "OK";
    }

    public String sucursales (final String identificador
            ,final String codigoentidadfederal
            ,final String localidad)  {

        //TODO LOG
        //System.out.println("Rest sucursales identificador ->" + identificador);
        return ce.sucursales(codigoentidadfederal, localidad);
    }


    public String info ( final String identificador, final Long idsucursal ) {
        //TODO LOG
        //System.out.println("Rest sucursales identificador ->" + identificador);
        return ce.infoSucursales(idsucursal);
    }

    public String precios ( final String identificador
                            ,final String codigoentidadfederal
                            ,final String localidad
                            ,final String codigos) {
        //TODO LOG
        //System.out.println("Rest sucursales identificador ->" + identificador);
        return ce.preciosSucursales(codigoentidadfederal, localidad, codigos);
    }
}
