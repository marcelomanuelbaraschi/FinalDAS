package contract;

import clients.exceptions.ClientException;

public interface SupermercadosServiceContract {
    //MarcaBean consultarMarca(String identificador, String marca) throws ClientException;
    String health(String identificador) throws ClientException;
}