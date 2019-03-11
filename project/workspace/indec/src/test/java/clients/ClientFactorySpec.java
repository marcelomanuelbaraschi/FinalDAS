package clients;

import clients.factory.ClientFactory;
import clients.factory.ClientType;
import contract.SupermercadosServiceContract;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class ClientFactorySpec {
    @Test
    public void test_client_factory_axis_success() {
        final String endpointUrl =  "http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/";
        final String targetNameSpace = "http://ws.SupermercadoAxisOne/";
        final HashMap<String, String> params = new HashMap<>();
        params.put("endpointUrl", endpointUrl);
        params.put("targetNameSpace", targetNameSpace);
        final Optional<SupermercadosServiceContract> clientFor =
                ClientFactory.getInstance().getClientFor(ClientType.AXIS, params);

        assertTrue(clientFor.isPresent());
        final SupermercadosServiceContract client = clientFor.get();
        assertTrue(client instanceof AxisClient);
    }


    @Test
    public void test_client_factory_rest_success() {
        final String url = "http://localhost:8001/supermercado_rest_one/supermercadoRestOne";
        final HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        final Optional<SupermercadosServiceContract> clientFor =
                ClientFactory.getInstance().getClientFor(ClientType.REST, params);

        assertTrue(clientFor.isPresent());
        final SupermercadosServiceContract client = clientFor.get();
        assertTrue(client instanceof RestClient);
    }

    @Test
    public void test_client_factory_cxf_success() {
        final String wsdlUrl = "http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl";
        final HashMap<String, String> params = new HashMap<>();
        params.put("wsdlUrl", wsdlUrl);
        final Optional<SupermercadosServiceContract> clientFor =
                ClientFactory.getInstance().getClientFor(ClientType.CXF, params);

        assertTrue(clientFor.isPresent());
        final SupermercadosServiceContract client = clientFor.get();
        assertTrue(client instanceof CXFClient);
    }
}