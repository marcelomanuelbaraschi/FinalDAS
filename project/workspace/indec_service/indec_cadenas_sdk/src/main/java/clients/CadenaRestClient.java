package clients;

import clients.exceptions.ClientException;
import clients.genericClients.RestClient;
import contract.CadenaServiceContract;

import static clients.constants.Constants.*;

public class CadenaRestClient extends RestClient implements CadenaServiceContract {

    public CadenaRestClient(final String url){
        super(url);
    }

    public String health() throws ClientException {
        final String url = getQuery(HEALTH);
        //TODO log
        return call(GET, String.format(url));
    }


    public String sucursales(final String codigoentidadfederal, final String localidad)
            throws ClientException
    {

        //Contruimos el formato de la query string.
        final String query = getQuery(SUCURSALES,CODIGO_ENTIDAD_FEDERAL,LOCALIDAD);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal,localidad);

        //Hacemos la llamada http
        final String sucursalesJson = call(GET, url);

        return sucursalesJson;

    }

    public String preciosSucursales(String codigoentidadfederal, String localidad, String codigos)
            throws ClientException
    {

        //Validamos parametros.
        if(codigos == null) throw new ClientException("El parametro codigos es null.");


        //Contruimos el formato de la query string
        final String query = getQuery(PRECIOSSUCURSALES,CODIGO_ENTIDAD_FEDERAL, LOCALIDAD, CODIGOS);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal, localidad, codigos);

        //Hacemos la llamada http
        final String preciosJson = call(POST, url);

        return preciosJson;
    }
}
