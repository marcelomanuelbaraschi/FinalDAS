package service.Actions;

import beans.Cadena;
import beans.Configuracion;
import clients.Tecnologia;
import clients.factory.CadenaClientFactory;
import contract.CadenaServiceContract;
import sdkObjects.Sucursal;

import java.util.List;

import static java.lang.Enum.valueOf;

public class ObtenerSucursalesDeCadena {

    public static Cadena execute
            (final String codigoentidadfederal,final String localidad,final Configuracion configuracion)
    {
        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());
        final Integer idCadena = configuracion.getIdCadena();
        final String nombreCadena = configuracion.getNombreCadena();
        Cadena cadena = new Cadena();
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

}
