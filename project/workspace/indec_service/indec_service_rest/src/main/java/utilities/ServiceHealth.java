package utilities;

import contract.CadenaServiceContract;
import db.beans.Configuracion;
import service.Cadenas.Cadenas;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static service.Cadenas.Cadenas.obtenerConfiguraciones;

public class ServiceHealth {

    public static String health
            (final Configuracion configuracion) {

        boolean isAvailable =  false;


        String status  =
                   "{"
                    + "nombreCadena: " + configuracion.getNombreCadena()
                    + ", "
                    + "idCadena: " + configuracion.getIdCadena()
                    + ", "
                    + "url: " + "'" + configuracion.getUrl() + "'"
                    + ", "
                    + "tecnologia: " +  configuracion.getNombreTecnologia()
                    + ", "
                    + "disponibilidad: %s" 
                    +
                   "}";

        try {
            CadenaServiceContract client = Cadenas.buildClient(configuracion);

            String okResponse;

            okResponse = client.health();

            isAvailable = okResponse.trim().toLowerCase().equals("ok");

            if(isAvailable) {

                return String.format( status, "ok" );

            }else {

                return String.format( status, "nok" );
            }

        } catch (Exception ex) {
             return String.format( status, "nok" );

        }
    }

    public static List<String> traversalHealth ()
    {
        List<Configuracion> configuraciones = obtenerConfiguraciones();

        return configuraciones
                .parallelStream()
                .map((config) -> health(config))
                .collect(toList());

    }

}
