package service;

import db.beans.Localidad;
import db.beans.Provincia;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Localizacion {


    public  List<Localidad> obtenerLocalidades() throws APIException
    {
        try {
            List<Bean> localidades = DaoFactory.getDao("Localidades")
                                               .select(null);

            return Arrays.asList(GSON.transform(localidades, Localidad[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public  List<Provincia> obtenerProvincias() throws APIException
    {
        try {
            List<Bean> provincias = DaoFactory.getDao("Provincias")
                                              .select(null);
            return Arrays.asList(GSON.transform(provincias, Provincia[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }


}
