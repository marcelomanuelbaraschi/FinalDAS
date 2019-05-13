package service;

import beans.*;
import sdkObjects.Sucursal;
import clients.*;
import clients.factory.CadenaClientFactory;
import contract.*;
import db.*;
import utilities.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Enum.valueOf;

public class Actions {

    public static List<CategoriaProducto> obtenerCategorias() throws APIException
    {
        try {
            List<Bean> categorias = DaoFactory.getDao("CategoriasProducto")
                                              .select(null);
            return Arrays.asList(GSON.transform(categorias, CategoriaProducto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Producto> obtenerProductos(Long idCategoria)
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

    public static List<Provincia> obtenerProvincias() throws APIException
    {
        try {
            List<Bean> provincias = DaoFactory.getDao("Provincias")
                                              .select(null);
            return Arrays.asList(GSON.transform(provincias, Provincia[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Localidad> obtenerLocalidades(final String codigoentidadfederal) throws APIException
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

    public static List<Cadena> obtenerCadenas() throws APIException
    {
        try {

            List<Bean> cadenas = DaoFactory.getDao("Cadenas")
                                           .select(null);

            return Arrays.asList(GSON.transform(cadenas, Cadena[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<Configuracion> obtenerConfiguraciones() throws APIException
    {
        try {

            List<Bean> configs = DaoFactory.getDao("Configuraciones")
                                           .select(null);

            return Arrays.asList(GSON.transform(configs, Configuracion[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }


    public static Cadena obtenerSucursalesDeCadena
            (final String codigoentidadfederal,final String localidad,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getTecnologia());
        final Long idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        Cadena cadena = new Cadena();
        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia,idCadena);

            List<Sucursal> sucursales = client.sucursales(codigoentidadfederal, localidad);

            if (sucursales.isEmpty()) throw new Exception("No hay sucursales en esta zona");

            cadena.setDisponibilidad("Disponible");
            cadena.setId(idCadena);
            cadena.setNombre(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;

        } catch (Exception e) {

            cadena.setDisponibilidad("No Disponible");
            cadena.setId(idCadena);
            cadena.setNombre(nombreCadena);
            //Asignamos las sucursales null
            cadena.setSucursales(null);
            return cadena;
        }
    }

    public static Cadena obtenerPreciosDeCadena
            (final String codigoentidadfederal,final String localidad
            ,final String codigos,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getTecnologia());
        final Long idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        Cadena cadena = new Cadena();
        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia,idCadena);

            List<Sucursal> sucursales = client.precios(codigoentidadfederal,localidad,codigos);

            if (sucursales.isEmpty()) throw new Exception("No hay sucursales en esta zona");

            cadena.setDisponibilidad("Disponible");
            cadena.setId(idCadena);
            cadena.setNombre(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;

        } catch (Exception e) {

            cadena.setDisponibilidad("No Disponible");
            cadena.setId(idCadena);
            cadena.setNombre(nombreCadena);
            //Asignamos las sucursales null
            cadena.setSucursales(null);
            return cadena;
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

