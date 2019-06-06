package service.Cadenas;

import Exceptions.APIException;
import db.beans.Cadena;
import db.beans.Configuracion;
import contract.CadenaServiceContract;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Cadenas {
    private static final Logger logger =
            LoggerFactory.getLogger(Cadenas.class);

    public static List<Cadena> obtenerCadenas() throws APIException
    {
        try {
            List<Bean> beans = DaoFactory.getDao("Cadenas").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Cadena[].class));
            }else{
                throw new APIException("GSON failed -> obtenerCadenas");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerCadenas:{}" + ex.getMessage());
        }
    }

    public static List<Configuracion> obtenerConfiguraciones() throws APIException

    {
        try {
            List<Bean> beans = DaoFactory.getDao("Configuraciones").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Configuracion[].class));

            }else{
                throw new APIException("GSON failed -> obtenerConfiguraciones");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerConfiguraciones:{}" + ex.getMessage());
        }

    }

    public static List<Cadena> obtenerSucursales (final String codigoentidadfederal,final String localidad,List<Configuracion> configuraciones)
    {
        return configuraciones
                .parallelStream()
                .map((config) -> obtenerSucursalesDeCadena(codigoentidadfederal,localidad,config))
                .collect(toList());

    }


    public static List<Cadena> obtenerPrecios (final String codigoentidadfederal,final String localidad,final String codigos,List<Configuracion> configuraciones)
    {
        return configuraciones
                .parallelStream()
                .map((config) -> obtenerPreciosDeCadena(codigoentidadfederal,localidad,codigos,config))
                .collect(toList());
    }

    public static Cadena obtenerSucursalesDeCadena (final String codigoentidadfederal,final String localidad,final Configuracion configuracion)
    {
            try{
                CadenaServiceContract client = CadenaFacade.buildClient(configuracion);

                String sucursalesJson = null;

                try {
                    sucursalesJson = client.sucursales(codigoentidadfederal,localidad);
                }catch (Exception clientException) {
                    logger.error( "Error SDK, {}", clientException.getMessage() );
                    return CadenaFacade.buildCadenaNoDisponible(configuracion);
                }

                List<Sucursal> sucursales = Arrays.asList(
                        GSON.toObject( sucursalesJson, Sucursal[].class )
                );

                final boolean haySucursales = sucursales != null && !(sucursales.isEmpty());

                if (haySucursales) {
                    return CadenaFacade.buildCadenaDisponible(configuracion, sucursales);
                } else {
                    return CadenaFacade.buildCadenaNoDisponible(configuracion);
                }
            }catch (APIException ex){
                return CadenaFacade.buildCadenaNoDisponible(configuracion);
            }
    }


    public static Cadena obtenerPreciosDeCadena (final String codigoentidadfederal,final String localidad,final String codigos,final Configuracion configuracion)
    {

        try{
            CadenaServiceContract client = CadenaFacade.buildClient(configuracion);

            String jsonresp = null;

            try {
                jsonresp = client.precios(codigoentidadfederal,localidad,codigos);
            }catch (Exception clientException) {
                logger.error( "Error SDK, {}", clientException.getMessage() );
                return CadenaFacade.buildCadenaNoDisponible(configuracion);
            }

            List<Sucursal> sucursales = Arrays.asList(
                    GSON.toObject( jsonresp, Sucursal[].class )
            );

            final boolean haySucursales = sucursales != null && !(sucursales.isEmpty());

            if (haySucursales) {
                return CadenaFacade.buildCadenaDisponible(configuracion, sucursales);
            } else {
                return CadenaFacade.buildCadenaNoDisponible(configuracion);
            }
        }catch (APIException ex){
            return CadenaFacade.buildCadenaNoDisponible(configuracion);
        }
    }

}



