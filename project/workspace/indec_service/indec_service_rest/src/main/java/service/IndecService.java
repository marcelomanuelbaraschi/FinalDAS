package service;

import beans.*;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import utilities.JsonMarshaller;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
public class IndecService {


    public Supplier<List<CategoriaProductoBean>> getCategorias = () ->  {
        try {
            CategoriaProductoBean categoria = new CategoriaProductoBean();
            Dao dao = DaoFactory.getDao("CategoriasProducto", "");
            List<Bean> categorias = dao.select(categoria);
            return Arrays.asList(JsonMarshaller.transform(categorias, CategoriaProductoBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }
    };

    public Function <Long,List<ProductoBean>> getProductos = (idCategoria)-> {
        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(idCategoria);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            return Arrays.asList(JsonMarshaller.transform(productos, ProductoBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }

    };

    public Supplier<List<ProvinciaBean>> getProvincias = () ->  {
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            return Arrays.asList(JsonMarshaller.transform(provincias, ProvinciaBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }
    };

    public Function <String,List<LocalidadBean>> getLocalidades = (codigoentidadfederal) -> {
        try {
            LocalidadBean localidad = new LocalidadBean();
            localidad.setCodigoEntidadFederal(codigoentidadfederal);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            return Arrays.asList(JsonMarshaller.transform(localidades, LocalidadBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }
    };

    public Supplier<List<CadenaBean>> getCadenas = () -> {
        try {
            CadenaBean cadena = new CadenaBean();
            Dao dao = DaoFactory.getDao("Cadenas", "");
            List<Bean> cadenas = dao.select(cadena);
            return Arrays.asList(JsonMarshaller.transform(cadenas, CadenaBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }
    };

    protected List<CadenaServiceConfigBean> configs() throws IndecServiceException {
        try {
            CadenaServiceConfigBean config = new CadenaServiceConfigBean();
            Dao dao = DaoFactory.getDao("CadenasServicesConfigs", "");
            List<Bean> configs = dao.select(config);
            return Arrays.asList(JsonMarshaller.transform(configs, CadenaServiceConfigBean[].class));
        } catch (SQLException ex) {
            throw new IndecServiceException(ex);
        }
    }

    /*
    protected List<Cadena> asyncompararPrecios(String codigoentidadfederal, String localidad, String codigos) throws IndecServiceException {
        return new IndecComparador(this.configs()).compararPrecios(codigoentidadfederal, localidad, codigos);
    }


    protected List<Cadena> compararPrecios(String codigoentidadfederal, String localidad, String codigos) throws IndecServiceException {
        try {
            return (new IndecComparador(this.configs()).compararPrecios(codigoentidadfederal, localidad, codigos));
        } catch (IndecServiceException ex) {
            throw new IndecServiceException(ex);
        }
    }


    protected List<Cadena> getSucursales(String codigoentidadfederal, String localidad) throws IndecServiceException {
        try {
            return (new IndecComparador(this.configs()).consultarSucursales(codigoentidadfederal, localidad));
        } catch (IndecServiceException ex) {
            throw new IndecServiceException(ex);
        }
    }*/

     class IndecServiceException extends RuntimeException {

        public IndecServiceException(final Exception ex) {
            super(ex.getMessage(), ex.getCause());
        }

        public IndecServiceException(final String message) {
            super(message);
        }
    }
}



















































    /*public List<CategoriaProductoBean> categorias()throws IndecServiceException;
    public List<Cadena> compararPrecios(final String codigoentidadfederal,final String localidad,final String codigos)throws IndecServiceException;
    public List<Cadena> sucursales (final String codigoentidadfederal,final String localidad)throws IndecServiceException;
    public List<ProductoBean> productos(final Long idCategoria)throws IndecServiceException;
    public List<ProvinciaBean> provincias()throws IndecServiceException;
    public List<LocalidadBean> localidades(final String codigoentidadfederal)throws IndecServiceException;
    public List<CadenaBean> cadenas()throws IndecServiceException;
    public List<CadenaServiceConfigBean> configs()throws IndecServiceException ; //TODO: NO VA ACA
    public List<Cadena> asyncompararPrecios(final String codigoentidadfederal, final String localidad, final String codigos)
            throws RuntimeException;*/

