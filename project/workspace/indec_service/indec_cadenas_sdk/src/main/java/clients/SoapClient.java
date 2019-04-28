package clients;

import cadenasObjects.Response;
import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import utils.JsonMarshaller;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;

public class SoapClient implements CadenaServiceContract {

    private final String wsdlUrl;

    public SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public static CadenaServiceContract create(final String wsdlUrl) throws ClientException {
        if (wsdlUrl == null) throw new ClientException ("Could not create SoapClient, the provided wsdlUrl is null");
        final SoapClient soapClient = new SoapClient(wsdlUrl);
        if (soapClient == null) throw new ClientException ("Could not create SoapClient");
        return soapClient;
    }

    private <A> Object executeMethod(final String methodName, final A... params) throws ClientException {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        try (final Client client = dcf.createClient(wsdlUrl)) {

            final Object[] res = client.invoke(methodName, params);

            Long statusCode = Long.parseLong(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());

            if (statusCode >= 500)
                throw new ClientException("ENDPOINT IS DOWN = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());
            if (statusCode >= 400)
                throw new ClientException("BAD REQUEST = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());

            if(res == null || res.length == 0) return null;

            return res[0];

        } catch (final Exception e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    @Override
    public String health() throws ClientException {
        final Object object = executeMethod(HEALTH);
        final String jsonBean = object.toString();
        //TODO log
        return jsonBean;
    }

    @Override
    //TODO update and test
    public List<Sucursal> sucursales(String codigoentidadfederal, String localidad) throws ClientException {
        final Object object = executeMethod(SUCURSALES,codigoentidadfederal,localidad);
        final String responseJson = object.toString();
        final Response resp = JsonMarshaller.toObject(responseJson , Response.class);
        //TODO log
        if(resp.getCodigo()==0) {
            final Sucursal[] arrsucs = JsonMarshaller.toObject(resp.getJson() , Sucursal[].class);
            return Stream.of(arrsucs).collect(Collectors.toList());
        }
        else {
            throw new ClientException(resp.getMensaje());
        }
    }

    @Override
    //TODO update and test
    public List<Sucursal> precios(String codigoentidadfederal, String localidad, List <String> codigos) throws ClientException {
        final Object object = executeMethod(PRECIOS,codigoentidadfederal,localidad, codigos.stream().collect(Collectors.joining(",")));
        final String responseJson = object.toString();
        final Response resp = JsonMarshaller.toObject(responseJson , Response.class);
        //TODO log
        if(resp.getCodigo()==0) {
            final Sucursal[] arrpsucs = JsonMarshaller.toObject(resp.getJson() , Sucursal[].class);
            return Stream.of(arrpsucs).collect(Collectors.toList());
        }
        else {
            throw new ClientException(resp.getMensaje());
        }
    }

}