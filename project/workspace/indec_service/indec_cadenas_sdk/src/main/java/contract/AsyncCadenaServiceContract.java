package contract;

import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsyncCadenaServiceContract {
    CompletableFuture<String> health() throws ClientException;
}
