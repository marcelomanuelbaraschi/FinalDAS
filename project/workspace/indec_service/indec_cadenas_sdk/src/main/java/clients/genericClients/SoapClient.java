package clients.genericClients;

import clients.exceptions.ClientException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.OutFaultChainInitiatorObserver;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

public class SoapClient {

    private final String wsdlUrl;

    protected SoapClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    protected  <A> Object executeMethod(final String methodName, final A... params)
            throws ClientException
    {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = null;

        try {
            client  =  dcf.createClient( wsdlUrl );
            final Object[] res = client.invoke( methodName, params );
            if (res == null || res.length == 0) {
                throw new ClientException( "Failed Invoking Client" );
            }
            handleError( client );
            return res[0];

        }catch (ClientException ex) {
            throw new ClientException( "ENDPOINT " + wsdlUrl + " IS DOWN : " + ex.getMessage() );
        } catch (IOException ex) {
            throw new ClientException( "ENDPOINT " + wsdlUrl + " IS DOWN : " + ex.getMessage() );
        } catch (Exception ex) {
            throw new ClientException( "ENDPOINT " + wsdlUrl + " IS DOWN : " + ex.getMessage() );
        }finally {
            try {
                client.close();
            } catch (Exception e) {
                throw new ClientException( "No se pudo cerrar el cliente SOAP" );
            }
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
