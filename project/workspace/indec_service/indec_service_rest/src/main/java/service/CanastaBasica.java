package service;

import beans.CategoriaProducto;
import beans.Producto;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;
import ws.FutureOps;
import ws.WebService;

import javax.ws.rs.container.AsyncResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
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
