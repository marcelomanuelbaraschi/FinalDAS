import cadenasObjects.InfoSucursal;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class cxf_one_tests {

    final String wsdlUrl = "http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl";

    @Test
    public void health_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final String confirmation = client.health("INDEC");
            assertEquals(confirmation.toLowerCase(), "ok");
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final List<Sucursal> sucursales = client.sucursales("INDEC","AR-X","Capital");
            assertNotNull(sucursales);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_codigoentidadfederal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final List<Sucursal> sucursales = client.sucursales("INDEC",null,"Capital");
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_sucursales_endpoint_with_localidad_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final List<Sucursal> sucursales = client.sucursales("INDEC","AR-X",null);
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_precios_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X","Capital", codigos);
            assertTrue(true);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_precios_endpoint_with_codigoentidadfederal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("INDEC",null,"Capital", codigos);
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_precios_endpoint_with_localidad_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            List<String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X",null, codigos);
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_precios_endpoint_with_codigos_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");
            final List<Sucursal> preciosSucursales = client.precios("INDEC","AR-X","Capital", null);
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }

    @Test
    public void call_to_info_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final List<InfoSucursal> sucursal = client.info("INDEC",1L);
            assertFalse(sucursal.isEmpty());
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void call_to_info_endpoint_with_idsucursal_null_should_fail() {
        try {
            final CadenaServiceContract client = ClientFactory.getInstance().clientFor(Tecnologia.SOAP, wsdlUrl);
            final List<InfoSucursal> sucursal = client.info("INDEC",null);
            fail("Deberia haber saltado una ClientException");
        } catch (ClientException e) {
            assertTrue(e.getMessage(),true);
        }
    }
}
