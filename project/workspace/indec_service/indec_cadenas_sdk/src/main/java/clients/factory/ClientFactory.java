package clients.factory;

import clients.RestClient;
import clients.SoapClient;
import clients.Tecnologia;
import contract.CadenaServiceContract;

public class ClientFactory implements IClientFactory {

    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    @Override
    public CadenaServiceContract clientFor(final Tecnologia tecnologia, final String url)  throws RuntimeException {
        if (url == null)
            throw new RuntimeException ("Could not create a client, the provided url is null");
        if (tecnologia.equals(Tecnologia.SOAP)) {
            return SoapClient.create(url);
        } else if (tecnologia.equals(Tecnologia.REST)) {
            return RestClient.create(url);
        } else  throw new RuntimeException ("Could not create a client, check the provided parameters");
    }
}