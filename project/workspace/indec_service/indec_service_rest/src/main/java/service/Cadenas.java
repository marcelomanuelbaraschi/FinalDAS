package service;

import beans.CadenaBean;
import beans.Configuracion;
import clients.Tecnologia;
import clients.factory.CadenaClientFactory;
import contract.CadenaServiceContract;
import db.Bean;
import db.DaoFactory;
import sdkObjects.Sucursal;
import utilities.GSON;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Enum.valueOf;
import static java.util.stream.Collectors.toList;

public class Cadenas {

    public static List<CadenaBean> obtenerCadenas() throws APIException
    {
        try {

            List<Bean> cadenas = DaoFactory.getDao("Cadenas")
                    .select(null);

            return Arrays.asList(GSON.transform(cadenas, CadenaBean[].class));

        } catch (SQLException ex) {
            throw new APIException(ex);
        }
    }

    public static List<CadenaBean> obtenerSucursales
        (final String codigoentidadfederal
        ,final String localidad
        ,final List<Configuracion> configuraciones)
    {
        return configuraciones.parallelStream()
                .map((config) -> obtenerSucursales(codigoentidadfederal,localidad,config))
                .collect(toList());
    }

    public static CadenaBean obtenerSucursales
        (final String codigoentidadfederal
        ,final String localidad
        ,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());
        final Integer idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        CadenaBean cadena = new CadenaBean();

        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia,idCadena);

            List<Sucursal> sucursales = client.sucursales(codigoentidadfederal, localidad);

            if (sucursales.isEmpty()) throw new Exception("No hay sucursales en esta zona");

            cadena.setDisponibilidad("Disponible");
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;

        } catch (Exception e) {

                cadena.setDisponibilidad("No Disponible");
                cadena.setIdCadena(idCadena);
                cadena.setNombreCadena(nombreCadena);
                //Asignamos las sucursales null
                cadena.setSucursales(null);
                return cadena;
            }
        }

    public static List<CadenaBean> obtenerPrecios
        (final String codigoentidadfederal
        ,final String localidad
        ,final String codigos
        ,final List<Configuracion> configuraciones)throws APIException
    {

        return configuraciones.parallelStream()
                .map((config) ->
                        obtenerPrecios(codigoentidadfederal
                                      ,localidad
                                      ,codigos
                                      ,config)
                ).collect(toList());

    }

    public static CadenaBean obtenerPrecios
        (final String codigoentidadfederal
        ,final String localidad
        ,final String codigos
        ,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());
        final Integer idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();

        List<Sucursal> sucursales;
        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia,idCadena);

            sucursales = client.precios(codigoentidadfederal,localidad,codigos);

            if (sucursales.isEmpty()) {
                CadenaBean cadena = new CadenaBean();
                cadena.setDisponibilidad("No Hay Sucursales en esta zona");
                cadena.setIdCadena(idCadena);
                cadena.setNombreCadena(nombreCadena);
                //Asignamos las sucursales null
                cadena.setSucursales(null);
                return cadena;
            }
        } catch (Exception e) {
            CadenaBean cadena = new CadenaBean();
            cadena.setDisponibilidad("No Disponible");
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales null
            cadena.setSucursales(null);
            return cadena;
        }
            CadenaBean cadena = new CadenaBean();
            cadena.setDisponibilidad("Disponible");
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;
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


}



