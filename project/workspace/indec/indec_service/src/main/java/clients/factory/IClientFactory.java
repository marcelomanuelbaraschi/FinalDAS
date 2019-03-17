package clients.factory;


import clients.Tecnologia;
import contract.SupermercadosServiceContract;

import java.util.Optional;

public interface IClientFactory {
    Optional<SupermercadosServiceContract> getClient(final Tecnologia tecnologia, final String url);
}