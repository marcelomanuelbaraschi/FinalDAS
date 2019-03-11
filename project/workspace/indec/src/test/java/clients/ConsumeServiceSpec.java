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


    private static void consumeHealth (final SupermercadosServiceContract client) throws ClientException {
        final String identificador = "GOB";

        System.out.println("consuminHealth");
        client.health(identificador);

//        System.out.println("consultarPlan");
//        final PlanBean plan0 = client.consultarPlan(identificador, 999L);
//        System.out.println(plan0.toString());

    }


    @Test
    public void test_axis_one_health(){

        final String endpointUrl = "http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/";
        final String targetNameSpace = "http://ws.SupermercadoAxisOne/";
        final HashMap<String, String> params = new HashMap<>();
        params.put("endpointUrl", endpointUrl);
        params.put("targetNameSpace", targetNameSpace);

        final SupermercadosServiceContract client =
                ClientFactory.getInstance().getClientFor(ClientType.AXIS, params).get();


        try {
            final String confirmation = client.health("GOB");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }
    }

    @Test
    public void test_cxf_one_health(){
        final String wsdlUrl = "http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl";
        final HashMap<String, String> params = new HashMap<>();
        params.put("wsdlUrl", wsdlUrl);
        final Optional<SupermercadosServiceContract> clientFor =
                ClientFactory.getInstance().getClientFor(ClientType.CXF, params);
        assertTrue(clientFor.isPresent());
        try {

            final String confirmation = clientFor.get().health("GOB");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }

    }

    @Test
    public void test_rest_one_health(){
        final String url = "http://localhost:8001/supermercado_rest_one/supermercadoRestOne";
        final HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        final Optional<SupermercadosServiceContract> clientFor =
                ClientFactory.getInstance().getClientFor(ClientType.REST, params);

        assertTrue(clientFor.isPresent());
        try {

            final String confirmation = clientFor.get().health("GOB");
            System.out.println(confirmation);
            assertTrue(true);
        } catch (ClientException e) {
            fail();
        }
    }



}