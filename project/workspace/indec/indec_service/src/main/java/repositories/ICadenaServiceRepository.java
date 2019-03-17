package repositories;

import contract.SupermercadosServiceContract;

import java.util.Optional;

public interface ICadenaServiceRepository {

    Optional<SupermercadosServiceContract> query  (Long cadenaId);


}
