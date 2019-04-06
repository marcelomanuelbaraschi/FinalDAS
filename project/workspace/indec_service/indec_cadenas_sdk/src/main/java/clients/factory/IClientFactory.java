package clients.factory;
import clients.Tecnologia;
import contract.CadenaServiceContract;

public interface IClientFactory {
    CadenaServiceContract clientFor(final Tecnologia tecnologia, final String url) throws RuntimeException;
}