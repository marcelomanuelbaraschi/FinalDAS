package service;

import db.beans.CategoriaProducto;
import db.beans.CriterioBusquedaProducto;
import db.beans.Producto;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;
import utilities.ListUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
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

    public static List<Producto> obtenerProductos() throws APIException
    {
        try {

            List<Bean> productos = DaoFactory.getDao("Productos")
                                             .select(null);
            return Arrays.asList(GSON.transform(productos, Producto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

    public static List<Producto> obtenerProductos(final CriterioBusquedaProducto criterio) throws APIException
    {
        List<Producto> productos = obtenerProductos();

        List<Producto> productosEncontrados = new ArrayList<>();

        List<Producto> porCategoria = buscarProductosPorCategoria(productos, criterio.getIdCategoria());
        List<Producto> porPalabraClave = buscarProductosPorPalabraClave(productos, criterio.getPalabraclave());
        List<Producto> porPalabraCodigos = buscarProductosPorCodigos (productos,criterio.getCodigos());

        productosEncontrados.addAll(porCategoria);
        productosEncontrados.addAll(porPalabraClave);
        productosEncontrados.addAll(porPalabraCodigos);

        return productosEncontrados;
    }


    private static List<Producto> buscarProductosPorCategoria(final List<Producto> productos, final Short idcategoria)
    {
        if(productos == null)
        {
            throw new APIException("buscarProductosPorCategoria : El parametros productos es null..");
        }
        if(idcategoria == null)
        {
            return new ArrayList<Producto>();
        }
        List<Producto> productosEncontrados = productos.stream().filter(p -> p.getIdCategoria().equals(idcategoria)).collect(toList());
        return productosEncontrados;

    }

    private static List<Producto> buscarProductosPorPalabraClave(final List<Producto> productos, final String palabraclave) throws APIException
    {
        if(productos == null)
        {
            throw new APIException("buscarProductosPorCategoria : El parametros productos es null..");
        }
        if(palabraclave == null)
        {
            return new ArrayList<Producto>();
        }

        List<Producto> productosEncontrados = productos.stream().filter((p) -> {
            return p.getNombreProducto().contains(palabraclave) || p.getNombreMarca().contains(palabraclave) || p.getNombreCategoria().contains(palabraclave);
        }).collect(toList());
        return productosEncontrados;


    }


    private static List<Producto> buscarProductosPorCodigos(final List<Producto> productos, final String codigos) throws APIException
    {
        if(productos == null)
        {
            throw new APIException("buscarProductosPorCategoria : El parametros productos es null..");
        }
        if(codigos == null)
        {
            return new ArrayList<Producto>();
        }

        List<String> lcodigos = ListUtils.asList(codigos);

        List<Producto> productosEncontrados = productos.stream().filter((p) -> {
            return lcodigos.contains(p.getCodigoDeBarras());
        }).collect(toList());

        return productosEncontrados;

    }
}
