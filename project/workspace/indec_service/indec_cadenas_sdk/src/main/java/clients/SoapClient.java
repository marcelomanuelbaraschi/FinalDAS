package clients;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import static constants.Constants.*;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;

public class SoapClient implements CadenaServiceContract {

    private final String wsdlUrl;

    public SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public static CadenaServiceContract create(final String wsdlUrl) throws ClientException {
        if (wsdlUrl == null) throw new ClientException ("Could not create SoapClient, the provided wsdlUrl is null");
        final SoapClient soapClient = new SoapClient(wsdlUrl);
        if (soapClient == null) throw new ClientException ("Could not create RestClient");
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
            throw new ClientException("ENDPOINT IS dOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonBean = object.toString();
        System.out.println("[GET health][jsonBean {"+jsonBean+"}]");
        return jsonBean;
    }

       /* @Override
    public MarcaBean consultarMarca(final String identificador, final String marca) throws ClientException {
        final Object object = executeMethod(CONSULTAR_MARCA ,identificador, marca);
        final String jsonMarcaBean = object.toString();
        log.info("[GET consultarPlan][object {}][jsonPlanBean = {}]", object, jsonMarcaBean);
        return JsonUtils.toObject(jsonMarcaBean, MarcaBean.class);
    }*/

}