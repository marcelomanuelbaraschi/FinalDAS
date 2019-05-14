import sdkObjects.Sucursal;
import clients.CadenaSoapClient;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class axis_one_tests {

    final String wsdlUrl = "http://localhost:8000/cadena_axis_one/services/CadenaAxisOne?wsdl";
/*
    @Test
    public void health_should_success() {
        try  {
           final CadenaServiceContract client = ClientFactory.clientFor(wsdlUrl,Tecnologia.SOAP,1L);
            final String confirmation =  client.health();
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }*/

   @Test
    public  void call_to_sucursales_endpoint_should_success() {
        try {
            final CadenaServiceContract client = new CadenaSoapClient (wsdlUrl,1);
            final List<Sucursal> sucursales =
                    client.sucursales("AR-X","Capital");
            System.out.println(sucursales);
            assertNotNull(sucursales);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }
    /*
    @Test
    public void call_to_precios_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.clientFor(wsdlUrl,Tecnologia.SOAP,1L);

            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");

            final List<Sucursal> preciosSucursales =
                    client.precios("AR-X","Capital", codigos);

            assertTrue(true);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }*/
}
