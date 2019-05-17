package service;

import db.beans.Cadena;
import db.beans.Configuracion;
import clients.Tecnologia;
import clients.factory.CadenaClientFactory;
import contract.CadenaServiceContract;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Enum.valueOf;
import static java.util.stream.Collectors.toList;

public class Cadenas {

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

    public static List<Cadena> obtenerSucursales
        (final String codigoentidadfederal
        ,final String localidad
        ,final List<Configuracion> configuraciones)
    {
        return configuraciones.parallelStream()
                .map((config) -> obtenerSucursales(codigoentidadfederal,localidad,config))
                .collect(toList());
    }

    public static Cadena obtenerSucursales
        (final String codigoentidadfederal
        ,final String localidad
        ,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());
        final Integer idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        Cadena cadena = new Cadena();

        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia);

            String sucursalesJson = client.sucursales(codigoentidadfederal, localidad);

            List<Sucursal> sucursales =
                    Arrays.asList(
                            GSON.toObject(sucursalesJson
                                    ,Sucursal[].class)
                    );;



            if (sucursales.isEmpty()) throw new Exception("No hay sucursales en esta zona");


            for (Sucursal s : sucursales){
                s.setIdCadena(idCadena);
            }

            cadena.setDisponible(true);
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;

        } catch (Exception e) {

                cadena.setDisponible(false);
                cadena.setIdCadena(idCadena);
                cadena.setNombreCadena(nombreCadena);
                LinkedList<Sucursal> sucs = new LinkedList<Sucursal>();
                cadena.setSucursales(sucs);
                return cadena;
            }
        }

    public static List<Cadena> obtenerPrecios
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

    public static Cadena obtenerPrecios
        (final String codigoentidadfederal
        ,final String localidad
        ,final String codigos
        ,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());
        final Integer idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        Cadena cadena = new Cadena();

        try {

            CadenaServiceContract client = CadenaClientFactory.clientFor(url,tecnologia);

            List<Sucursal> sucursales =
                    Arrays.asList(
                            GSON.toObject(client.precios(codigoentidadfederal,localidad,codigos)
                                          ,Sucursal[].class)
                    );

            if (sucursales.isEmpty()) throw new Exception("No hay sucursales en esta zona");

            for (Sucursal s : sucursales){
                s.setIdCadena(idCadena);
            }

            cadena.setDisponible(true);
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales
            cadena.setSucursales(sucursales);
            return cadena;

        } catch (Exception e) {

            cadena.setDisponible(false);
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //lista vacia
            LinkedList<Sucursal> sucs = new LinkedList<Sucursal>();
            cadena.setSucursales(sucs);
            return cadena;
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


}



