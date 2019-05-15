package service.Actions;

import beans.Producto;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerProductos {

    public static List<Producto> execute()
            throws APIException
    {
        try {
            List<Bean> productos = DaoFactory.getDao("Productos")
                    .select(null);
            return Arrays.asList(GSON.transform(productos, Producto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

}
