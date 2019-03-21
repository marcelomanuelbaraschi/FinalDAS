import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class sdkSpecs {

    protected Logger log = LoggerFactory.getLogger(sdkSpecs.class);

    @Test
    public void test_axis_one_health() { //Walmart
        final String wsdlUrl = "http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_cxf_one_health() { //Jumbo
        final String wsdlUrl = "http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_rest_one_health() { //Carrefour
        final String url = "http://localhost:8001/supermercado_rest_one/supermercadoRestOne";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

}
