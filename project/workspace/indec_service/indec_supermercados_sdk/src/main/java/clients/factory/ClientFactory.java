package clients.factory;

import clients.*;
import clients.Tecnologia;
import clients.RestClient;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;

public class ClientFactory implements IClientFactory {

    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    @Override
    public CadenaServiceContract clientFor(final Tecnologia tecnologia, final String url)  throws ClientException {
        if (url == null)
            throw new ClientException ("Could not create a client, the provided url is null");
        if (tecnologia.equals(Tecnologia.SOAP)) {
            return SoapClient.create(url);
        } else if (tecnologia.equals(Tecnologia.REST)) {
            return RestClient.create(url);
        } else  throw new ClientException ("Could not create a client, check the provided parameters");
    }
}