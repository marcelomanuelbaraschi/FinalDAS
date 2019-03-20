package clients.factory;

import clients.*;
import clients.Tecnologia;
import clients.RestClient;
import contract.SupermercadosServiceContract;
import java.util.Optional;

public class ClientFactory implements IClientFactory {

    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    @Override
    public Optional<SupermercadosServiceContract> getClient(final Tecnologia tecnologia, final String url) {
        if (url == null)
            return Optional.empty();
        if (tecnologia.equals(Tecnologia.SOAP)) {
            return SoapClient.create(url);
        } else if (tecnologia.equals(Tecnologia.REST)) {
            return RestClient.create(url);
        } else return Optional.empty();
    }
}