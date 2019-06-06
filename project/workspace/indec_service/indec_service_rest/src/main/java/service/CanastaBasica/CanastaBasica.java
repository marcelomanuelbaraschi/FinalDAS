package service.CanastaBasica;
import Exceptions.APIException;
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

import static java.util.Optional.*;

public class CanastaBasica {

    private static final Logger logger =
            LoggerFactory.getLogger(CanastaBasica.class);

    public  static List<CategoriaProducto> obtenerCategorias() throws APIException
    {
        try {
            List<Bean> beans = DaoFactory.getDao("CategoriasProducto").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, CategoriaProducto[].class));
            }else{
                throw new APIException("GSON failed -> obtenerCategorias");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerCategorias:{}" + ex.getMessage());
        }

    }

    private static List<Producto> obtenerProductos () throws APIException
    {

        try {
            List<Bean> beans = DaoFactory.getDao("Productos").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Producto[].class));
            }else{
                throw new APIException("GSON failed -> obtenerProductos");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerProductos:{}" + ex.getMessage());
        }
    }

    public  static List<Producto> obtenerProductos(final CriterioBusquedaProducto criterio) throws APIException
    {
        if(criterio == null) {
            throw new APIException("Se debe proveer un criterio para la busqueda de productos" );
        }

        List<Producto> productos = obtenerProductos();

        if(criterio.getIdCategoria() == null & criterio.getMarca() == null)
            return productos;

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

        List<Producto> list = new ArrayList<>();

        for (Producto producto : productos) {
            if (porCategoria.test(producto)) {
                if (porMarca.test(producto)) {
                    list.add(producto);
                }
            }
        }
        return list;
    }

    public  static List<Producto>  buscarProductos(final String palabraclave) throws APIException
    {
        if(palabraclave == null) {
            throw new APIException("Se debe proveer una palabraclave para la busqueda de productos");
        }

        List<Producto> productos = obtenerProductos();
        Predicate<Producto> buscarPorPalabraClave =  p -> {
            return     p.getNombreProducto().trim().toLowerCase().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreMarca().trim().toLowerCase().contains(palabraclave.toLowerCase().trim())
                    || p.getNombreCategoria().trim().toLowerCase().contains(palabraclave.toLowerCase().trim());
        };


        List<Producto> list = new ArrayList<>();
        for (Producto producto : productos) {
            if (buscarPorPalabraClave.test(producto)) {
                list.add(producto);
            }
        }

        return list;
    }

    public  static List<Producto> buscarProductosPorCodigos(final String codigos) throws APIException
    {
        if(codigos == null) {
            throw new APIException("Se debe proveer una lista de codigos para la busqueda de productos");
        }

        List<Producto> productos = obtenerProductos();
        List<String> lcodigos = new ArrayList<>();
        for (String c : ListUtils.asList(codigos)) {
            String trim = c.trim();
            lcodigos.add(trim);
        }

        Predicate<Producto>  porCodigoDeBarras  = p -> {
            return lcodigos.contains(p.getCodigoDeBarras());
        };

        List<Producto> list = new ArrayList<>();
        for (Producto producto : productos) {
            if (porCodigoDeBarras.test(producto)) {
                list.add(producto);
            }
        }

        return list;
    }
}
