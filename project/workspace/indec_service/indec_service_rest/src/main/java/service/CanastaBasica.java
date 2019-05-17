package service;

import db.beans.CategoriaProducto;
import db.beans.Producto;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static javax.ws.rs.core.Response.status;

public class CanastaBasica {
    private static final Logger logger =
            LoggerFactory.getLogger(CanastaBasica.class);


    private CanastaBasica() {
    }

    public static List<CategoriaProducto> obtenerCategorias()throws APIException {
            try {
                List<Bean> categorias = DaoFactory.getDao("CategoriasProducto")
                                                  .select(null);
                return Arrays.asList(GSON.transform(categorias, CategoriaProducto[].class));
            } catch (SQLException ex) {
                throw new APIException(ex);
            }

    }

    public static List<Producto> obtenerProductos()
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
