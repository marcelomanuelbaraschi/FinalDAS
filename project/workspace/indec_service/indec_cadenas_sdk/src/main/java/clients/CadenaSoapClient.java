package clients;

import sdkObjects.Sucursal;
import clients.exceptions.ClientException;
import clients.genericClients.SoapClient;
import contract.CadenaServiceContract;
import utils.GSON;
import java.util.Arrays;
import java.util.List;
import static clients.constants.Constants.*;
import static java.util.stream.Collectors.joining;


public class CadenaSoapClient extends SoapClient implements CadenaServiceContract {

    private final Integer idCadena;

    public CadenaSoapClient(final String wsdlUrl,final Integer idCadena){
        super(wsdlUrl);
        this.idCadena = idCadena;
     }

     public String health()
            throws ClientException
    {
        final Object object = executeMethod(HEALTH);
        final String jsonBean = object.toString();
        return jsonBean;
    }


    public List<Sucursal> sucursales
            (final String codigoentidadfederal
            ,final String localidad)
            throws ClientException
    {
        //Ejecutamos el metodo en el servicio con el cual mantenemos conexion
        final Object object = executeMethod(SUCURSALES,codigoentidadfederal,localidad);

        //Obtenemos el json de respuesta
        final String sucursalesJson = object.toString();


        //Pasamos de un json a una Lista de Sucursales
        List<Sucursal> sucursales = Arrays.asList(GSON.toObject(sucursalesJson,Sucursal[].class));

        //Le asignamos a las sucursales un  idCadena
        if (!sucursales.isEmpty()) {

            for (Sucursal sucursal : sucursales) {
                sucursal.setIdCadena(this.idCadena);
            }
            return sucursales;
        }
        else throw new ClientException("La lista de Sucursales de la Cadena " + idCadena +  " esta vacia");
    }


    public List<Sucursal> precios
            (final String codigoentidadfederal,
             final String localidad, String codigos)
            throws ClientException
    {

        //Ejecutamos el metodo en el servicio con el cual mantenemos conexion
        final Object object = executeMethod(PRECIOS,codigoentidadfederal,localidad,codigos);

        //Obtenemos el json de respuesta
        final String preciosJson = object.toString();

        //Pasamos de un json a una Lista de Sucursales
        List<Sucursal> precioSucursales = Arrays.asList(GSON.toObject(preciosJson,Sucursal[].class));

        //Le asignamos a las sucursales un  idCadena
        if (!precioSucursales.isEmpty()) {

            for (Sucursal sucursal : precioSucursales) {
                sucursal.setIdCadena(this.idCadena);
            }
            return precioSucursales;
        }
        else throw new ClientException("La lista de Sucursales de la Cadena " + idCadena +  " esta vacia");

    }

}