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
import java.util.*;
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


    public static List<Producto> obtenerProductos(final CriterioBusquedaProducto criterio) throws APIException
    {
        List<Producto> productos;
        List<Bean> beans;

        if(criterio == null){
            throw new APIException("El criterio de busqueda del producto es es null");
        }

        try {
            beans = DaoFactory.getDao("Productos").select(null);
        }catch (SQLException ex) {
            throw new APIException(ex);
        }

        productos = Arrays.asList(GSON.transform(beans, Producto[].class));

        if(productos == null || beans == null){
            throw new APIException("Fallo al obtener los productos");
        }


        if(criterio.getPalabraclave() == null & criterio.getIdCategoria() == null) {
            return productos;
        }

        List<Producto> productosEncontrados = new ArrayList<>();

        productosEncontrados.addAll(buscarProductosPorCategoria(productos, criterio.getIdCategoria()));
        productosEncontrados.addAll(buscarProductosPorPalabraClave(productos, criterio.getPalabraclave()));

        //removemos duplicados
        return productosEncontrados.stream().distinct().collect(toList());
    }


    private static List<Producto> buscarProductosPorCategoria(final List<Producto> productos, final Short idcategoria)
    {
        if(idcategoria == null) return new ArrayList<Producto>();

        List<Producto> productosEncontrados = productos.stream().filter(p -> p.getIdCategoria().equals(idcategoria)).collect(toList());
        return productosEncontrados;
    }

    private static List<Producto> buscarProductosPorPalabraClave(final List<Producto> productos, final String palabraclave)
    {
        if(palabraclave == null) return new ArrayList<Producto>();

        List<Producto> productosEncontrados = productos.stream().filter((p) -> {
            return     p.getNombreProducto().toLowerCase().trim().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreMarca().toLowerCase().trim().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreCategoria().toLowerCase().trim().contains(palabraclave.toLowerCase().trim());
        }).collect(toList());
        return productosEncontrados;

    }


    private static List<Producto> buscarProductosPorCodigos(final List<Producto> productos, final String codigos)
    {
        if(codigos == null) return new ArrayList<Producto>();

        List<String> lcodigos = ListUtils.asList(codigos).stream().map(c -> c.trim()).collect(toList());

        List<Producto> productosEncontrados = productos.stream().filter((p) -> {
            return lcodigos.contains(p.getCodigoDeBarras());
        }).collect(toList());

        return productosEncontrados;

    }
}
