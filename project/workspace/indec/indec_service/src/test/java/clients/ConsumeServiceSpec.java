package clients;

import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import contract.SupermercadosServiceContract;
import org.junit.Test;
import java.util.HashMap;
import java.util.Optional;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ConsumeServiceSpec {

    @Test
    public void test_axis_one_health(){ //Walmart
        final String endpointUrl = "http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/";
        //final String wsdlUrl = "http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl";
        final String targetNameSpace = "http://ws.SupermercadoAxisOne/";
        final HashMap<String, String> params = new HashMap<>();
        params.put("endpointUrl", endpointUrl);
        params.put("targetNameSpace", targetNameSpace);
        //params.put("wsdlUrl", wsdlUrl);
        final  Optional<SupermercadosServiceContract> maybeClient =
                ClientFactory.getInstance().getClientFor(ClientType.AXIS, params);
        assertTrue(maybeClient.isPresent());
        try {
            final String confirmation = maybeClient.get().health("INDEC");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }
    }

    @Test
    public void test_cxf_one_health(){ //Jumbo
        final String wsdlUrl = "http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl";
        final HashMap<String, String> params = new HashMap<>();
        params.put("wsdlUrl", wsdlUrl);
        final Optional<SupermercadosServiceContract> maybeClient =
                ClientFactory.getInstance().getClientFor(ClientType.CXF, params);
        assertTrue(maybeClient.isPresent());
        try {
            final String confirmation = maybeClient.get().health("INDEC");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }

    }

    @Test
    public void test_rest_one_health(){ //Carrefour
        final String url = "http://localhost:8001/supermercado_rest_one/supermercadoRestOne";
        final HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        final Optional<SupermercadosServiceContract> maybeClient =
                ClientFactory.getInstance().getClientFor(ClientType.REST, params);
        assertTrue(maybeClient.isPresent());
        try {
            final String confirmation = maybeClient.get().health("INDEC");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }
    }

}