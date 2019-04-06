package clients;

import cadenasObjects.InfoSucursal;
import cadenasObjects.PreciosSucursal;
import cadenasObjects.Response;
import cadenasObjects.Sucursal;
import contract.CadenaServiceContract;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;

public class SoapClient implements CadenaServiceContract {

    private final String wsdlUrl;

    public SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public static CadenaServiceContract create(final String wsdlUrl) throws RuntimeException {
        if (wsdlUrl == null) throw new RuntimeException ("Could not create SoapClient, the provided wsdlUrl is null");
        final SoapClient soapClient = new SoapClient(wsdlUrl);
        if (soapClient == null) throw new RuntimeException ("Could not create RestClient");
        return soapClient;
    }

    private <A> Object executeMethod(final String methodName, final A... params) throws RuntimeException {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        try (final Client client = dcf.createClient(wsdlUrl)) {

            final Object[] res = client.invoke(methodName, params);

            Long statusCode = Long.parseLong(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());

            if (statusCode >= 500)
                throw new RuntimeException("ENDPOINT IS DOWN = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());
            if (statusCode >= 400)
                throw new RuntimeException("BAD REQUEST = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());

            if(res == null || res.length == 0) return null;

            return res[0];

        } catch (final Exception e) {
            throw new RuntimeException("ENDPOINT IS dOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    @Override
    public String health(final String identificador) throws RuntimeException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonBean = object.toString();
        //TODO log
        return jsonBean;
    }

    @Override
    //TODO update and test
    public List<Sucursal> sucursales(String identificador, String codigoentidadfederal, String localidad) throws RuntimeException {
        final Object object = executeMethod(SUCURSALES, identificador,codigoentidadfederal,localidad);
        final String responsejson = object.toString();
        final Response resp = JsonUtils.toObject(responsejson , Response.class);
        //TODO log
        if(resp.getCodigo()==0) {
            final Sucursal[] arrsucs = JsonUtils.toObject(resp.getJson() , Sucursal[].class);
            return Stream.of(arrsucs).collect(Collectors.toList());
        }
        else {
            throw new RuntimeException(resp.getMensaje());
        }
    }

    @Override
    //TODO update and test
    public List<PreciosSucursal> precios(String identificador, String codigoentidadfederal, String localidad, List <String> codigos) throws RuntimeException {
        final Object object = executeMethod(PRECIOS, identificador,codigoentidadfederal,localidad, codigos.stream().collect(Collectors.joining(",")));
        final String responsejson = object.toString();
        final Response resp = JsonUtils.toObject(responsejson , Response.class);
        //TODO log
        if(resp.getCodigo()==0) {
            final PreciosSucursal[] arrpsucs = JsonUtils.toObject(resp.getJson() , PreciosSucursal[].class);
            return Stream.of(arrpsucs).collect(Collectors.toList());
        }
        else {
            throw new RuntimeException(resp.getMensaje());
        }
    }

    @Override
    //TODO update and test
    public List<InfoSucursal> info(String identificador, Long idSucursal) throws RuntimeException {
        final Object object = executeMethod(INFO, identificador, idSucursal);
        final String responsejson = object.toString();
        final Response resp = JsonUtils.toObject(responsejson , Response.class);
        //TODO log
        if(resp.getCodigo()==0) {
            final InfoSucursal[] arrpsucs = JsonUtils.toObject(resp.getJson() , InfoSucursal[].class);
            return Stream.of(arrpsucs).collect(Collectors.toList());
        }
        else {
            throw new RuntimeException(resp.getMensaje());
        }
    }

}