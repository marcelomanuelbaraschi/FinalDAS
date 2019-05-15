package service.Actions;

import beans.Localidad;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerLocalidades {

    public static List<Localidad> execute() throws APIException
    {
        try {
            List<Bean> localidades = DaoFactory.getDao("Localidades")
                    .select(null);

            return Arrays.asList(GSON.transform(localidades, Localidad[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }
}
