package clients.factory;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;

public interface IClientFactory {
    CadenaServiceContract clientFor(final Tecnologia tecnologia, final String url) throws ClientException;
}