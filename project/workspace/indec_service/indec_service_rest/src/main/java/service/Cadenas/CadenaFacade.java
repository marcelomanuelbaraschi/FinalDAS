package service.Cadenas;
import Exceptions.APIException;
import db.beans.Cadena;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.CadenaClientFactory;
import contract.CadenaServiceContract;
import db.beans.Configuracion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Enum.valueOf;

public class CadenaFacade {

    private static final Logger logger = LoggerFactory.getLogger(CadenaFacade.class);

    public static CadenaServiceContract buildClient(Configuracion configuracion) throws APIException{

        if(esConfiguracionValida(configuracion)){
            throw new APIException("La configuracion del cliente no es valida: " + configuracion.toString());
        }

        final String url = configuracion.getUrl();
        final Tecnologia tecnologia = valueOf(Tecnologia.class, configuracion.getNombreTecnologia());

        CadenaServiceContract client;

        try {
            client = CadenaClientFactory.clientFor(url,tecnologia);
            if(client == null){
                throw new  APIException("El cliente creado por el sdk es null");
            }
            return client;

        }catch(ClientException cex) {
            throw new  APIException("SDK Error,{}" + cex.getMessage());
        }
    }

    private static boolean esConfiguracionValida (Configuracion configuracion){

            if (configuracion == null) {
                return false;
            }

            if( configuracion.getUrl() != null
                && !(configuracion.getUrl().trim().equals(""))
                && configuracion.getNombreTecnologia() != null
                && !(configuracion.getNombreTecnologia().trim().equals(""))
                && configuracion.getIdCadena() != null
                && configuracion.getIdCadena() > 0
                && configuracion.getNombreCadena() != null
                && !(configuracion.getNombreCadena().trim().equals(""))

            ){
                return true;
            }
            else{
                return false;
            }
        }

    public static Cadena buildCadenaNoDisponible(Configuracion configuracion) {
        Cadena cadena = new Cadena();
        cadena.setDisponible(false);
        cadena.setIdCadena(configuracion.getIdCadena());
        cadena.setNombreCadena(configuracion.getNombreCadena());
        LinkedList<Sucursal> sucs = new LinkedList<Sucursal>();
        cadena.setSucursales(sucs);
        return cadena;
    }

    public static Cadena buildCadenaDisponible(Configuracion configuracion, List<Sucursal> sucursales) {

        Cadena cadena = new Cadena();
        for (Sucursal s : sucursales){
            s.setIdCadena(configuracion.getIdCadena());
        }
        cadena.setDisponible(true);
        cadena.setIdCadena(configuracion.getIdCadena());
        cadena.setNombreCadena(configuracion.getNombreCadena());
        //Asignamos las sucursales
        cadena.setSucursales(sucursales);
        return cadena;

    }

}
