package service.Actions;

import beans.Provincia;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerProvincias {

    public static List<Provincia> execute() throws APIException
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
