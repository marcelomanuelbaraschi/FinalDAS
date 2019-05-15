package service.Actions;

import beans.*;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerProductosPorPlato {

    public static List<Producto> execute(Integer idPlato) throws APIException{

        Plato plato = new Plato();
        plato.setIdPlato(idPlato);
        try {
            List<Bean> productos = DaoFactory.getDao("ProductosPorPlato")
                                              .select(plato);
            return Arrays.asList(GSON.transform(productos, Producto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }
}
