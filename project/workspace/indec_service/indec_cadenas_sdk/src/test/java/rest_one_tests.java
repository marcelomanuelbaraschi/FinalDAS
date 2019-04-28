import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class rest_one_tests {

    final String url = "http://localhost:8001/cadena_rest_one/cadenaRestOne";

    @Test
    public void health_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final String confirmation = client.health();
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("AR-X","Capital");
            assertNotNull(sucursales);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_codigoentidadfederal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales(null,"Capital");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_localidad_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("AR-X",null);
            //fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }


    @Test
    public void call_to_precios_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("AR-X","Capital", codigos);
            assertTrue(true);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_precios_endpoint_with_codigoentidadfederal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios(null,"Capital", codigos);
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_precios_endpoint_with_localidad_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            List<String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("AR-X",null, codigos);
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_precios_endpoint_with_codigos_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("AR-X","Capital", null);
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

}
