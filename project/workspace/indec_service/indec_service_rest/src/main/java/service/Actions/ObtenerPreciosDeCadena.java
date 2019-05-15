package service.Actions;

import beans.Cadena;
import beans.Configuracion;
import clients.Tecnologia;
import clients.factory.CadenaClientFactory;
import contract.CadenaServiceContract;
import sdkObjects.Sucursal;

import java.util.List;

import static java.lang.Enum.valueOf;

public class ObtenerPreciosDeCadena {

    public static Cadena execute
            (final String codigoentidadfederal,final String localidad
                    ,final String codigos,final Configuracion configuracion)
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
                Cadena cadena = new Cadena();
                cadena.setDisponibilidad("No Hay Sucursales en esta zona");
                cadena.setIdCadena(idCadena);
                cadena.setNombreCadena(nombreCadena);
                //Asignamos las sucursales null
                cadena.setSucursales(null);
                return cadena;
            }
        } catch (Exception e) {
            Cadena cadena = new Cadena();
            cadena.setDisponibilidad("No Disponible");
            cadena.setIdCadena(idCadena);
            cadena.setNombreCadena(nombreCadena);
            //Asignamos las sucursales null
            cadena.setSucursales(null);
            return cadena;
        }
        Cadena cadena = new Cadena();
        cadena.setDisponibilidad("Disponible");
        cadena.setIdCadena(idCadena);
        cadena.setNombreCadena(nombreCadena);
        //Asignamos las sucursales
        cadena.setSucursales(sucursales);
        return cadena;
    }
}

abstract class Cadena2 {}
class CadenaSuccess extends Cadena2 {}
class CadenaFailure extends Cadena2 {}

