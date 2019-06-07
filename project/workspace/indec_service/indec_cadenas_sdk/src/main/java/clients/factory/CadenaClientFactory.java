package clients.factory;

import clients.CadenaRestClient;
import clients.CadenaSoapClient;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;

public class CadenaClientFactory {

    public static CadenaServiceContract  clientFor (final String url,final String tecnologia)
            throws ClientException
    {
        final boolean isUrlValid = (url!= null) && (!url.trim().equals(""));
        final boolean isTecnologiaValida = (tecnologia!= null) && (!tecnologia.trim().equals(""));

        if(isUrlValid && isTecnologiaValida){

            if (tecnologia.toUpperCase().equals("REST")) {
                return new CadenaRestClient(url);
            }else if (tecnologia.toUpperCase().equals("SOAP")) {
                return new CadenaSoapClient(url);
            } else  throw new ClientException ("No se pudo crear el cliente, verifique los parametros..");

        }else{
            throw new ClientException ("No se pudo crear el cliente, verifique los parametros..");
        }

    }
}