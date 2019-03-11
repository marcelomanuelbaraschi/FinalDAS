package clients;
import beans.MarcaBean;
import clients.exceptions.ClientException;
import contract.SupermercadosServiceContract;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.util.Map;
import java.util.Optional;
import static constants.Constants.*;

public class AxisClient implements SupermercadosServiceContract {

    private final String endpointUrl;
    private final OMFactory fac;
    private final OMNamespace omNs;

    protected Logger log = LoggerFactory.getLogger(AxisClient.class);

    public AxisClient(final String endpointUrl
            , final String targetNameSpace
    ) {
        this.endpointUrl = endpointUrl;
        this.fac = OMAbstractFactory.getOMFactory();
        this.omNs = fac.createOMNamespace(targetNameSpace, "ns1");
    }

    public static Optional<SupermercadosServiceContract> create(final Map<String, String> params) {
        final String endpointUrl = params.getOrDefault(AXIS_PARAM_ENDP_URL, "");
        final String targetNameSpace = params.getOrDefault(AXIS_PARAM_TARGET, "");
        if (endpointUrl.isEmpty() || targetNameSpace.isEmpty())
            return Optional.empty();
        final AxisClient axisClient = new AxisClient(endpointUrl, targetNameSpace);
        return Optional.of(axisClient);
    }


    private void call(final OMElement method) throws ClientException {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));
            serviceClient.setOptions(opts);
            serviceClient.fireAndForget(method); // Note: this is the only line difference with executeMethod

        } catch (final AxisFault e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    private OMElement executeMethod(final OMElement method) throws ClientException {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));

            serviceClient.setOptions(opts);

            final OMElement res = serviceClient.sendReceive(method);

            if (res.getFirstElement().getText().equals("null"))
                throw new ClientException("ENDPOINT IS DOWN = null");

            return res;
        } catch (final AxisFault e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    private OMElement createMethod(final String methodName) {
        return fac.createOMElement(methodName, omNs);
    }

    private <A> OMElement createParam(final String paramName, final A paramValue) {
        final OMElement param = fac.createOMElement(paramName, omNs);
        param.setText(paramValue.toString());
        return param;
    }


    @Override
    public MarcaBean consultarMarca(final String identificador, final String marca) throws ClientException {
        final OMElement method = createMethod(CONSULTAR_MARCA);
        final OMElement param_id = createParam(IDENTIFICADOR, identificador);
        final OMElement param_marca = createParam(MARCA, marca);
        method.addChild(param_id);
        method.addChild(param_marca);
        final OMElement omElement = executeMethod(method); // response
        final OMElement returnValue = omElement.getFirstElement();
        final String jsonMarcaBean = returnValue.getText();
        log.info("[GET consultarMarca][method {}][jsonPlanBean = {}]", method, jsonMarcaBean);
        return JsonUtils.toObject(jsonMarcaBean, MarcaBean.class);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final OMElement method = createMethod(HEALTH);
        final OMElement param = createParam(IDENTIFICADOR, identificador);
        method.addChild(param);
        final OMElement omElement = executeMethod(method); // response
        final OMElement returnValue = omElement.getFirstElement();
        final String jsonHealth = returnValue.getText();
        log.info("[GET health][jsonHealth {}]", jsonHealth);
        return jsonHealth;
    }
}