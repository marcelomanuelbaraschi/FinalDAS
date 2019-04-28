package service;

import beans.*;
import comparador.IndecComparador;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import indecObjects.Cadena;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.JsonMarshaller;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndecServiceImpl implements IndecService{
    private static final Logger logger = LoggerFactory.getLogger(IndecServiceImpl.class);
    private static volatile IndecServiceImpl serviceInstance;

    private IndecServiceImpl(){}

    public static IndecServiceImpl getInstance(){
        if (serviceInstance == null){
            serviceInstance = new IndecServiceImpl();
        }

        return serviceInstance;
    }


    @Override
    public List<CategoriaProductoBean> categorias() throws IndecServiceException {
        try {
            CategoriaProductoBean categoria = new CategoriaProductoBean();
            Dao dao = DaoFactory.getDao("CategoriasProducto", "");
            List<Bean> categorias = dao.select(categoria);
            final  CategoriaProductoBean [] arrcategorias  = JsonMarshaller.toObject(JsonMarshaller.toJson(categorias),CategoriaProductoBean[].class);
            return Stream.of(arrcategorias).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo categorias:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Cadena> compararPrecios(String codigoentidadfederal, String localidad, String codigos) throws IndecServiceException {
        try {
           return (new IndecComparador(this.configs()).compararPrecios(codigoentidadfederal,localidad,codigos));
        } catch (IndecServiceException ex) {
            logger.error("Error en el metodo compararPrecios:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Cadena> sucursales(String codigoentidadfederal, String localidad) throws IndecServiceException {
        try {
            return (new IndecComparador(this.configs()).consultarSucursales(codigoentidadfederal,localidad));
        } catch (IndecServiceException ex) {
            logger.error("Error en el metodo sucursales:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<ProductoBean> productos(Long idCategoria) throws IndecServiceException {
        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(idCategoria);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            final  ProductoBean [] arrproductos  = JsonMarshaller.toObject(JsonMarshaller.toJson(productos) ,ProductoBean[].class);
            return Stream.of(arrproductos).collect(Collectors.toList());
        }
        catch(SQLException ex) {
           logger.error("Error en el metodo productos:{}",ex.getMessage());
           throw new IndecServiceException(ex.getMessage());
        }

    }

    @Override
    public List<ProvinciaBean> provincias() throws IndecServiceException {
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            final  ProvinciaBean []  arrprovincias  = JsonMarshaller.toObject(JsonMarshaller.toJson(provincias) ,ProvinciaBean[].class);
            return Stream.of(arrprovincias).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo provincias:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<LocalidadBean> localidades(String codigoentidadfederal) throws IndecServiceException {
        try {
            LocalidadBean localidad = new LocalidadBean();
            localidad.setCodigoEntidadFederal(codigoentidadfederal);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            final  LocalidadBean []  arrlocalidades  = JsonMarshaller.toObject(JsonMarshaller.toJson(localidades) ,LocalidadBean[].class);
            return Stream.of(arrlocalidades).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo localidades:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<CadenaBean> cadenas() throws IndecServiceException {
        try {
            CadenaBean cadena = new CadenaBean();
            Dao dao = DaoFactory.getDao("Cadenas", "");
            List<Bean> cadenas = dao.select(cadena);
            final  CadenaBean []  arrcadenas  = JsonMarshaller.toObject(JsonMarshaller.toJson(cadenas) ,CadenaBean[].class);
            return Stream.of(arrcadenas).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo cadenas:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }

    @Override
    public List<CadenaServiceConfigBean> configs() throws IndecServiceException {
        try {
            CadenaServiceConfigBean config = new CadenaServiceConfigBean();
            Dao dao = DaoFactory.getDao("CadenasServicesConfigs", "");
            List<Bean> configs = dao.select(config);
            final  CadenaServiceConfigBean []  arrconfigs  = JsonMarshaller.toObject(JsonMarshaller.toJson(configs) ,CadenaServiceConfigBean[].class);
            return Stream.of(arrconfigs).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo cadenas:{}",ex.getMessage());
            throw new IndecServiceException(ex.getMessage());
        }
    }



}
