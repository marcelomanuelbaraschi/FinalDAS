package api;

import beans.*;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import utilities.GSON;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class IndecAPI {


    public static List<CategoriaProducto> obtenerCategorias ()
            throws APIException
    {
        try {
            List<Bean> categorias = DaoFactory.getDao("CategoriasProducto")
                                              .select(null);
            return Arrays.asList(GSON.transform(categorias, CategoriaProducto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Producto> obtenerProductos (Long idCategoria)
            throws APIException
    {
        try {
            CriterioBusquedaProducto criterio = new CriterioBusquedaProducto();
            criterio.setIdCategoria(idCategoria);
            List<Bean> productos = DaoFactory.getDao("Productos")
                                             .select(criterio);
            return Arrays.asList(GSON.transform(productos, Producto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

    public static List<Provincia> obtenerProvincias()
            throws APIException
    {
        try {
            List<Bean> provincias = DaoFactory.getDao("Provincias")
                                              .select(null);
            return Arrays.asList(GSON.transform(provincias, Provincia[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Localidad> obtenerLocalidades(final String codigoentidadfederal)
            throws APIException
    {
        try {
            CriterioBusquedaLocalidad criterio = new CriterioBusquedaLocalidad();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            List<Bean> localidades = DaoFactory.getDao("Localidades")
                                               .select(criterio);
            return Arrays.asList(GSON.transform(localidades, Localidad[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Cadena> obtenerCadenas ()
            throws APIException
    {
        try {
            List<Bean> cadenas = DaoFactory.getDao("Cadenas")
                                           .select(null);
            return Arrays.asList(GSON.transform(cadenas, Cadena[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Configuracion> obtenerConfiguraciones ()
            throws APIException
    {
        try {
            Dao dao = DaoFactory.getDao("Configuraciones");
            List<Bean> configs = dao.select(null);
            return Arrays.asList(GSON.transform(configs, Configuracion[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    static class APIException extends RuntimeException {

        public APIException(final Exception ex) {
            super(ex.getMessage(), ex.getCause());
        }

    }
}



















































    /*public List<CategoriaProducto> categorias()throws IndecServiceException;
    public List<Cadena> compararPrecios(final String codigoentidadfederal,final String localidad,final String codigos)throws IndecServiceException;
    public List<Cadena> sucursales (final String codigoentidadfederal,final String localidad)throws IndecServiceException;
    public List<ProductoBean> productos(final Long idCategoria)throws IndecServiceException;
    public List<Provincia> provincias()throws IndecServiceException;
    public List<Localidad> localidades(final String codigoentidadfederal)throws IndecServiceException;
    public List<Cadena> cadenas()throws IndecServiceException;
    public List<Configuracion> configs()throws IndecServiceException ; //TODO: NO VA ACA
    public List<Cadena> asyncompararPrecios(final String codigoentidadfederal, final String localidad, final String codigos)
            throws RuntimeException;*/

