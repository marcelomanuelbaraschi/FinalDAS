package service;
import bean.CriterioBusquedaProductos;
import bean.CriterioLocalizacionSucursal;
import bean.Sucursal;
import db.Bean;
import db.DaoFactory;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import utilities.GSON;

public class CadenaAPI {



    private CadenaAPI(){ }

    public static List<Sucursal> sucursales (final String codigoentidadfederal
                                            ,final String localidad) throws Exception
    {

        if(codigoentidadfederal == null) throw new Exception("El parametro codigos es null.");
        if(localidad == null) throw new Exception("El parametro codigos es null.");

        try {

            CriterioLocalizacionSucursal criterio = new CriterioLocalizacionSucursal();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            criterio.setLocalidad(localidad);

            List<Bean> sucs = DaoFactory.getDao("Sucursales")
                                        .select(criterio);


            return Arrays.asList(GSON.transform(sucs, Sucursal[].class));

        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Sucursal> preciosSucursales(final String codigoentidadfederal
                                                  ,final String localidad
                                                  ,final String codigos) throws Exception
    {

        if(codigoentidadfederal == null) throw new Exception("El parametro codigos es null.");
        if(localidad == null) throw new Exception("El parametro codigos es null.");
        if(codigos == null) throw new Exception("El parametro codigos es null.");

        Pattern p = Pattern.compile("[0-9]+(,[0-9]+)+");
        if(!p.matcher(codigos).find()) throw new Exception("El parametro codigos no tiene el formato correcto.");

        try {

            CriterioBusquedaProductos criterio = new CriterioBusquedaProductos();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            criterio.setLocalidad(localidad);
            criterio.setCodigos(codigos);

            List<Bean> ps = DaoFactory.getDao("PreciosSucursales")
                                      .select(criterio);

            return Arrays.asList(GSON.transform(ps, Sucursal[].class));

        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    static class APIException extends RuntimeException {

        public APIException(final Exception ex) {
            super(ex.getMessage(), ex.getCause());
        }

    }

}
