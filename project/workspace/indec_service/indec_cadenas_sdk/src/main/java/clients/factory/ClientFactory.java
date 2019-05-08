package clients.factory;

import clients.CadenaRestClient;
import clients.CadenaSoapClient;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;

public class ClientFactory {

    private static ClientFactory instance = new ClientFactory();
    public ClientFactory getInstance(){
        return instance;
    }

    public CadenaServiceContract
    clientFor(final String url,final Tecnologia tecnologia,final Long idCadena)
            throws ClientException
    {
        if (tecnologia.equals(Tecnologia.REST)) {
            return new CadenaRestClient(url,idCadena);
        }else if (tecnologia.equals(Tecnologia.SOAP)) {
            return new CadenaSoapClient(url,idCadena);
        } else  throw new ClientException ("No se pudo crear el cliente, verifique los parametros..");
    }
}