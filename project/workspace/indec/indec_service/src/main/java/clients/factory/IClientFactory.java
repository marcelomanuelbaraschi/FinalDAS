package clients.factory;


import contract.SupermercadosServiceContract;

import java.util.Map;
import java.util.Optional;

public interface IClientFactory {
    Optional<SupermercadosServiceContract> getClientFor(final ClientType configTecno, final Map<String, String> params);
}