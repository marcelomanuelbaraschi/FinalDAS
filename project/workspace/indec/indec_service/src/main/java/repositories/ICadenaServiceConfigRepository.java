package repositories;

import beans.CadenaServiceConfig;
import contract.SupermercadosServiceContract;

import java.util.Optional;

public interface ICadenaServiceConfigRepository {

    Optional<SupermercadosServiceContract> query    (Long cadenaId);
    Optional<SupermercadosServiceContract> store    (CadenaServiceConfig config);
    Boolean activate   (Long cadenaId);
    Boolean deactivate (Long cadenaId);
    Boolean delete(Long cadenaId);

    /*
    Optional<SupermercadosServiceContract> query (Long cadenaId);
    Optional<SupermercadosServiceContract> update (Long cadenaId)
    Optional<SupermercadosServiceContract> store (CadenaServiceConfig config);
    Boolean activate (Long cadenaId);
    Boolean deactivate (Long cadenaId);
    */

}
