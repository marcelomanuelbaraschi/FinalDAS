package clients.genericClients;

import clients.exceptions.ClientException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class SoapClient {

    private final String wsdlUrl;

    protected SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    protected  <A> Object executeMethod(final String methodName, final A... params)
            throws ClientException
    {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        try (final Client client = dcf.createClient(wsdlUrl)) {

            HTTPConduit http = (HTTPConduit)client.getConduit();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(60 * 1000);
            httpClientPolicy.setReceiveTimeout(60 * 1000);
            http.setClient(httpClientPolicy);


            final Object[] res = client.invoke(methodName, params);
            if(res == null || res.length == 0) throw new ClientException("Failed Invoking Client");
            handleError(client);
            return res[0];

        } catch (final java.net.SocketTimeoutException te) {
            throw new ClientException("ENDPOINT IS DOWN = " + te.getMessage());
        } catch (Exception e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage());
        }
    }

    private void handleError(final Client client)
            throws ClientException
    {
        final Long statusCode = getStatusCode(client);

        if (statusCode >= 500) {
            final String err =
                    client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString();
            throw new ClientException("Server Error -> " + err);
        }
        if (statusCode >= 400){
            final String err =
                    client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString();
            throw new ClientException("Client Error -> " + err);

        }
    }

    private  Long getStatusCode (Client client)
    {
        return Long.parseLong(
                client.getResponseContext()
                        .get("org.apache.cxf.message.Message.RESPONSE_CODE")
                        .toString()
        );
    }

}
