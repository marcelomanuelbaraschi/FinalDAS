import cadenasObjects.InfoSucursal;
import cadenasObjects.PreciosSucursal;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class sdkSpecs {


    @Test
    public void test_axis_one_health() {
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
    public void test_rest_one_health() {
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
    public void test_rest_one_sucursales() {
        final String url = "http://localhost:8001/cadena_rest_one/cadenaRestOne";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("INDEC","AR-X","Capital");
            System.out.println(sucursales.toString());
            assertFalse(sucursales.isEmpty());
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_rest_one_precios() {
        final String url = "http://localhost:8001/cadena_rest_one/cadenaRestOne";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            List <String> codigos = new LinkedList<>();
            codigos.add("7798113151278");
            codigos.add("7798113151377");
            System.out.println(codigos.stream().collect(Collectors.joining(",")));
            final List<PreciosSucursal> preciosSucursales = client.precios("INDEC","AR-X","Capital", codigos);
            System.out.println(preciosSucursales.toString());
            assertFalse(preciosSucursales.isEmpty());//si fallo por aca es porque la lista es vacia
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_rest_one_info() {
        final String url = "http://localhost:8001/cadena_rest_one/cadenaRestOne";
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<InfoSucursal> infoSucursal = client.info("INDEC",1L);
            System.out.println(infoSucursal.toString());
            assertFalse(infoSucursal.isEmpty());//si fallo por aca es porque la lista es vacia
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void test_rest_two_health() {
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
    public void test_cxf_one_health() {
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
    public void test_cxf_two_health() {
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
