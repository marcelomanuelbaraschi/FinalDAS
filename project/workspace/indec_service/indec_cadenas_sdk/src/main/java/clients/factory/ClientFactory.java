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

    public static CadenaServiceContract clientFor(final String url,final Tecnologia tecnologia)
        throws ClientException
    {
        if (tecnologia.equals(Tecnologia.REST)) {
            return new CadenaRestClient(url);
        }else if (tecnologia.equals(Tecnologia.SOAP)) {
            return new CadenaSoapClient(url);
        } else  throw new ClientException ("Could not create a client, check the provided parameters");
    }
}