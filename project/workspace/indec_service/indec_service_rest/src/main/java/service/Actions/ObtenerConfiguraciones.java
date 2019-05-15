package service.Actions;

import beans.Configuracion;
import db.Bean;
import db.DaoFactory;

import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerConfiguraciones {

    public static List<Configuracion> execute() throws APIException
    {
        try {

            List<Bean> configs = DaoFactory.getDao("Configuraciones")
                    .select(null);

            return Arrays.asList(GSON.transform(configs, Configuracion[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

}
