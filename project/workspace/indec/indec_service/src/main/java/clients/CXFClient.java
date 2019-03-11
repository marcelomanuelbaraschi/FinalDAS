package clients;
import beans.MarcaBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static constants.Constants.*;
import clients.exceptions.ClientException;
import contract.SupermercadosServiceContract;

public class CXFClient implements SupermercadosServiceContract {

    private final String wsdlUrl;
    protected Logger log = LoggerFactory.getLogger(CXFClient.class);

    public CXFClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl; // "http://localhost:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl"
    }

    public static Optional<SupermercadosServiceContract> create(final Map<String, String> params) {
        final String wsdlUrl = params.getOrDefault(CXF_PARAM_WSDL_URL, "");
        if (wsdlUrl.isEmpty())
            return Optional.empty();
        final CXFClient cxfClient = new CXFClient(wsdlUrl);
        return Optional.of(cxfClient);
    }

    private <A> Object executeMethod(final String methodName, final A... params) throws ClientException {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        try (final Client client = dcf.createClient(wsdlUrl)) {

            final Object[] res = client.invoke(methodName, params);

            Long statusCode =
                    Long.parseLong(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());

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
    public MarcaBean consultarMarca(final String identificador, final String marca) throws ClientException {
        final Object object = executeMethod(CONSULTAR_MARCA ,identificador, marca);
        final String jsonMarcaBean = object.toString();
        log.info("[GET consultarPlan][object {}][jsonPlanBean = {}]", object, jsonMarcaBean);
        return JsonUtils.toObject(jsonMarcaBean, MarcaBean.class);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonPlanBean = object.toString();
        log.info("[GET health][jsonPlanBean {}]", jsonPlanBean);
        return jsonPlanBean;
    }
}