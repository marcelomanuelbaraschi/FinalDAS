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
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

public class CanastaBasica {
    private static final Logger logger =
            LoggerFactory.getLogger(CanastaBasica.class);

    public static  List<CategoriaProducto> obtenerCategorias()throws APIException
    {
        try {
            List<Bean> categorias = DaoFactory.getDao("CategoriasProducto")
                                              .select(null);
            return Arrays.asList(GSON.transform(categorias, CategoriaProducto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

    private static List<Producto>  obtenerProductos() throws APIException
    {
        List<Producto> productos;
        List<Bean> beans;
        
        try {
            beans = DaoFactory.getDao("Productos").select(null);
        }catch (SQLException ex) {
            throw new APIException(ex);
        }

        productos = Arrays.asList(GSON.transform(beans, Producto[].class));

        if(productos == null || beans == null){
            throw new APIException("Fallo al obtener los productos");
        }
        return productos;

    }

    public static  List<Producto> obtenerProductos(final CriterioBusquedaProducto criterio) throws APIException
    {
        if(criterio == null){
            throw new APIException("El criterio de busqueda del producto es es null");
        }

        List<Producto> productos = obtenerProductos();

        if(criterio.getIdCategoria() == null & criterio.getMarca() == null) {
            return productos;
        }

        Predicate<Producto> porCategoria = p -> {

            if (criterio.getIdCategoria() == null) {
                return true;
            } else {
                return p.getIdCategoria().equals(criterio.getIdCategoria());
            }

        };

        Predicate<Producto> porMarca = p -> {

            if (criterio.getMarca() == null) {
                return true;
            } else {
                return (p.getNombreMarca().trim().toLowerCase().equals(criterio.getMarca().trim().toLowerCase()));
            }
        };

        return productos.stream()
                        .filter(porCategoria)
                        .filter(porMarca)
                        .collect(toList());

    }

    public static List<Producto> buscarProductos(final String palabraclave) throws APIException
    {
        if(palabraclave == null) return new ArrayList<Producto>();

        List<Producto> productos = obtenerProductos();

        Predicate<Producto> buscarPorPalabraClave =  p -> {
            return     p.getNombreProducto().trim().toLowerCase().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreMarca().trim().toLowerCase().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreCategoria().trim().toLowerCase().contains(palabraclave.toLowerCase().trim());
        };


        return productos.stream().filter(buscarPorPalabraClave).collect(toList());

    }

    public static List<Producto> buscarProductosPorCodigos(final String codigos) throws APIException
    {
        List<Producto> productos = obtenerProductos();

        if(codigos == null) return new ArrayList<Producto>();

        List<String> lcodigos = ListUtils.asList(codigos).stream().map(c -> c.trim()).collect(toList());

        Predicate<Producto>  porPalabraClave  = p -> {
            return lcodigos.contains(p.getCodigoDeBarras());
        };

        return productos.stream().filter(porPalabraClave).collect(toList());

    }
}
