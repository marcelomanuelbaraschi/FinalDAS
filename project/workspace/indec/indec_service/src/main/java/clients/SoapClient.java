package clients;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import static constants.Constants.*;
import clients.exceptions.ClientException;
import contract.SupermercadosServiceContract;

public class SoapClient implements SupermercadosServiceContract {

    private final String wsdlUrl;
    protected Logger log = LoggerFactory.getLogger(SoapClient.class);

    public SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public static Optional<SupermercadosServiceContract> create(final String wsdlUrl) {
        if (wsdlUrl == null)
            return Optional.empty();
        final SoapClient soapClient = new SoapClient(wsdlUrl);
        return Optional.of(soapClient);
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

   /* @Override
    public MarcaBean consultarMarca(final String identificador, final String marca) throws ClientException {
        final Object object = executeMethod(CONSULTAR_MARCA ,identificador, marca);
        final String jsonMarcaBean = object.toString();
        log.info("[GET consultarPlan][object {}][jsonPlanBean = {}]", object, jsonMarcaBean);
        return JsonUtils.toObject(jsonMarcaBean, MarcaBean.class);
    }*/

    @Override
    public String health(final String identificador) throws ClientException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonBean = object.toString();
        log.info("[GET health][jsonBean {}]", jsonBean);
        return jsonBean;
    }
}