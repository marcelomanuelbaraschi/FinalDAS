
package clients;
import clients.exceptions.ClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static constants.Constants.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AsyncRestClient  {

    /*private final String url;
    private final HttpClient client;

    private String url() {
        return this.url;
    }
    private HttpClient client() {
        return this.client;
    }

    private AsyncRestClient(final String url) {
        this.url = url;
        this.client = HttpClientBuilder.create().build();
    }

    public static AsyncCadenaServiceContract create(final String url) throws ClientException {
        if (url == null) throw new ClientException ("Could not create RestClient, the provided url is null");
        final AsyncRestClient restClient = new AsyncRestClient(url);
        if (restClient == null) throw new ClientException ("Could not create RestClient");
        return restClient;
    }

    private String buildQueryString(final String base, final String... params) {
        final String target = "/" + base + "?";
        final String args = Stream.of(params)
                .map(p -> p + "=%s")
                .collect(Collectors.joining("&"));
        final String arg = (params.length > 1 ? args : params[0] + "=%s");
        return target + arg;
    }

    private HttpUriRequest constructURI (final String method,final String callTo) throws ClientException {

        URI uri = URI.create(url() + callTo);
        switch (method) {
            case POST:
                HttpPost postReq = new HttpPost();
                postReq.setURI(uri);
                return postReq;
            case GET:
                HttpGet getReq = new HttpGet();
                getReq.setURI(uri);
                return getReq;
            case PUT:
                HttpPut putReq = new HttpPut();
                putReq.setURI(uri);
                return putReq;
            default:
                throw new ClientException("Invalid method: " + method);
        }
    }

    private HttpEntity extractHttpEntity (HttpResponse httpResponse) throws ClientException {
        return httpResponse.getEntity();
    }

    private HttpResponse execute (HttpUriRequest uri) throws ClientException {
        try {
            return client().execute(uri);
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    private HttpResponse filterBadResponse(HttpResponse response) throws ClientException {
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 500) throw new ClientException("SERVER ERROR = " + response.toString());
        if (statusCode >= 400) throw new ClientException("CLIENT ERROR = " + response.toString());
        return response;

    }

    private String httpEntityToString (HttpEntity entity){
        try {
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    private CompletableFuture<String> callAsync(final String method, final String callTo) throws ClientException  {

        return supplyAsync(() -> constructURI(method, callTo))
                .thenApply(this::execute)
                .thenApply(this::filterBadResponse)
                .thenApply(this::extractHttpEntity)
                .thenApply(this::httpEntityToString);
        }


    public CompletableFuture<String> health() {
        final String url = buildQueryString(HEALTH);
        return callAsync(GET, String.format(url));
    }*/


}


