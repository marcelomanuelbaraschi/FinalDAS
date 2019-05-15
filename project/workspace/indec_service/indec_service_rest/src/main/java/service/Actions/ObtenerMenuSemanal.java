package service.Actions;

import beans.Menu;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerMenuSemanal {


    public static List<Menu> execute() throws APIException
    {
        try {
            List<Bean> menuSemanal = DaoFactory.getDao("MenuSemanal")
                    .select(null);
            return Arrays.asList(GSON.transform(menuSemanal, Menu[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

}
