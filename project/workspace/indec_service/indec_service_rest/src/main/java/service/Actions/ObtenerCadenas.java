package service.Actions;

import beans.Cadena;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerCadenas {

    public static List<Cadena> execute () throws APIException
    {
        try {

            List<Bean> cadenas = DaoFactory.getDao("Cadenas")
                                            .select(null);

            return Arrays.asList(GSON.transform(cadenas, Cadena[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

}
