import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class sdkSpecs {


    @Test
    public void test_axis_one_health() { //Walmart
        final String wsdlUrl = "http://localhost:8000/cadena_axis_one/services/CadenaAxisOne?wsdl";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }



    @Test
    public void test_rest_one_health() { //Libertad
        final String url = "http://localhost:8001/cadena_rest_one/cadenaRestOne";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_rest_two_health() { //Disco
        final String url = "http://localhost:8002/cadena_rest_two/cadenaRestTwo";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_cxf_one_health() { //Jumbo
        final String wsdlUrl = "http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_cxf_two_health() { //Carrefour
        final String wsdlUrl = "http://localhost:8004/cadena_cxf_two/services/cadena_cxf_two?wsdl";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }


}
