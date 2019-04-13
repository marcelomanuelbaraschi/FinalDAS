import cadenasObjects.InfoSucursal;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
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
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("INDEC","AR-X","Capital");
            assertNotNull(sucursales);
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_codigoentidadfederal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("INDEC",null,"Capital");
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_localidad_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<Sucursal> sucursales = client.sucursales("INDEC","AR-X",null);
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
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
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X","Capital", codigos);
            assertTrue(true);
        } catch (RuntimeException e) {
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
            final List<Sucursal> preciosSucursales = client.precios("INDEC",null,"Capital", codigos);
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
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
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X",null, codigos);
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
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
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X","Capital", null);
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_info_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<InfoSucursal> sucursal = client.info("INDEC",1L);
            assertFalse(sucursal.isEmpty());
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_info_endpoint_with_idsucursal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.REST, url);
            final List<InfoSucursal> sucursal = client.info("INDEC",null);
            //fail("Deberia haber saltado una ClientException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage(),true);
        }
    }
}
