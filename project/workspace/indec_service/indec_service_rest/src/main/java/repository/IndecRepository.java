package repository;

import repository.exceptions.RepositoryException;
import beans.*;
import repository.db.Bean;
import repository.db.Dao;
import repository.db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.JsonMarshaller;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class IndecRepository {
    private static final Logger logger = LoggerFactory.getLogger(IndecRepository.class);
    private static volatile IndecRepository repositoryInstance;

    private IndecRepository(){}

    public static IndecRepository getInstance(){
        if (repositoryInstance == null){
            repositoryInstance = new IndecRepository();
        }

        return repositoryInstance;
    }

    public List<CategoriaProductoBean> categorias() throws RepositoryException {
        try {
        CategoriaProductoBean categoria = new CategoriaProductoBean();
        Dao dao = DaoFactory.getDao("CategoriasProducto", "");
        List<Bean> categorias = dao.select(categoria);
        final  CategoriaProductoBean [] arrcategorias  = JsonMarshaller.toObject(JsonMarshaller.toJson(categorias),CategoriaProductoBean[].class);
        return Stream.of(arrcategorias).collect(Collectors.toList());
        }
        catch(SQLException ex) {
        logger.error("Error en el metodo categorias:{}",ex.getMessage());
        throw new RepositoryException(ex.getMessage());
       }
    }

    public List<Bean> productos(final Long idCategoria) throws RepositoryException{
        //TODO SI EL idcategoria es null traer todos los productos
        //TODO PAGINACION ??
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
            throw new RepositoryException(ex.getMessage());
        }
    }

    public List<ProvinciaBean> provincias() throws RepositoryException{
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            final  ProvinciaBean []  arrprovincias  = JsonMarshaller.toObject(JsonMarshaller.toJson(provincias) ,ProvinciaBean[].class);
            return Stream.of(arrprovincias).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo provincias:{}",ex.getMessage());
            throw new RepositoryException(ex.getMessage());
        }
    }

    public List<LocalidadBean> localidades(final String codigoentidadfederal) throws RepositoryException{
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
            throw new RepositoryException(ex.getMessage());
        }
    }

    public List<CadenaBean> cadenas() throws RepositoryException{
        try {
            CadenaBean cadena = new CadenaBean();
            Dao dao = DaoFactory.getDao("Cadenas", "");
            List<Bean> cadenas = dao.select(cadena);
            final  CadenaBean []  arrcadenas  = JsonMarshaller.toObject(JsonMarshaller.toJson(cadenas) ,CadenaBean[].class);
            return Stream.of(arrcadenas).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo cadenas:{}",ex.getMessage());
            throw new RepositoryException(ex.getMessage());
        }
    }

    public List<CadenaServiceConfigBean> configs() throws RepositoryException {
        try {
            CadenaServiceConfigBean config = new CadenaServiceConfigBean();
            Dao dao = DaoFactory.getDao("CadenasServicesConfigs", "");
            List<Bean> configs = dao.select(config);
            final  CadenaServiceConfigBean []  arrconfigs  = JsonMarshaller.toObject(JsonMarshaller.toJson(configs) ,CadenaServiceConfigBean[].class);
            return Stream.of(arrconfigs).collect(Collectors.toList());
        }
        catch(SQLException ex) {
            logger.error("Error en el metodo cadenas:{}",ex.getMessage());
            throw new RepositoryException(ex.getMessage());
        }
    }

}
