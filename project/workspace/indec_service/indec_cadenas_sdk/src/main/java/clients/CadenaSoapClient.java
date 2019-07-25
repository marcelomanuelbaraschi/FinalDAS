package clients;

import clients.exceptions.ClientException;
import clients.genericClients.SoapClient;
import contract.CadenaServiceContract;
import static clients.constants.Constants.*;


public class CadenaSoapClient extends SoapClient implements CadenaServiceContract {

    public CadenaSoapClient(final String wsdlUrl){
        super(wsdlUrl);
     }

     public String health()throws ClientException
    {
        final Object object = executeMethod(HEALTH);
        final String jsonBean = object.toString();
        return jsonBean;
    }


    public String sucursales
            (final String codigoentidadfederal
            ,final String localidad)throws ClientException
    {
        //Ejecutamos el metodo en el servicio con el cual mantenemos conexion
        final Object object = executeMethod(SUCURSALES,codigoentidadfederal,localidad);

        //Obtenemos el json de respuesta
        final String sucursalesJson = object.toString();

        return sucursalesJson;

    }


    public String preciosSucursales
            (final String codigoentidadfederal
            ,final String localidad
            ,final String codigos)throws ClientException
    {

        //Ejecutamos el metodo en el servicio con el cual mantenemos conexion
        final Object object = executeMethod(PRECIOSSUCURSALES,codigoentidadfederal,localidad,codigos);

        //Obtenemos el json de respuesta
        final String preciosJson = object.toString();

        return preciosJson;

    }

}