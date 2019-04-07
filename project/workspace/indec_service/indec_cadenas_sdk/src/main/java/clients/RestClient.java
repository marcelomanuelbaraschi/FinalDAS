package clients;

import cadenasObjects.InfoSucursal;
import cadenasObjects.Response;
import cadenasObjects.Sucursal;
import contract.CadenaServiceContract;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;

public class RestClient implements CadenaServiceContract {

    private final String url;
    private final HttpClient client;

    private String url() {
        return this.url;
    }
    private HttpClient client() {
        return this.client;
    }

    public RestClient(final String url) {
        this.url = url;
        this.client = HttpClientBuilder.create().build();
    }

    public static CadenaServiceContract create(final String url) throws RuntimeException {
        if (url == null) throw new RuntimeException ("Could not create RestClient, the provided url is null");
        final RestClient restClient = new RestClient(url);
        if (restClient == null) throw new RuntimeException ("Could not create RestClient");
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

    private BiFunction<String, String, HttpUriRequest> HTTPFactory = (method, callTo) -> {

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
                throw new IllegalArgumentException("Invalid method: " + method);
        }
    };

    public String getQuery(final String base, final String... params) {
        final String target = "/" + base + "?";
        final String args = Stream.of(params)
                .map(p -> p + "=%s")
                .collect(Collectors.joining("&"));
        final String arg = (params.length > 1 ? args : params[0] + "=%s");
        return target + arg;
    }

    private String call(final String method, final String callTo) throws RuntimeException {

        try {
            final HttpResponse resp = client().execute(HTTPFactory.apply(method, callTo));
            final HttpEntity responseEntity = resp.getEntity();

            final int statusCode = resp.getStatusLine().getStatusCode();

            if (statusCode >= 500)
                throw new RuntimeException("SERVER ERROR = " + resp.toString());
            if (statusCode >= 400)
                throw new RuntimeException("CLIENT ERROR = " + resp.toString());

            final String jsonBean = EntityUtils.toString(responseEntity);

            return jsonBean;
        } catch (final IOException e) {
            throw new RuntimeException("ENDPOINT IS DOWN = " + e.getMessage());
        }
    }



    @Override
    public String health(final String identificador) throws RuntimeException {
        final String url = buildQueryString(HEALTH, IDENTIFICADOR);
        //TODO log
        return call(GET, String.format(url, identificador));
    }

    @Override
    public List<Sucursal> sucursales(final String identificador, final String codigoentidadfederal, final String localidad) throws RuntimeException {

        final String query = getQuery(SUCURSALES, IDENTIFICADOR, CODIGO_IDENTIDAD_FEDERAL,LOCALIDAD);
        final String url = String.format(query, identificador, codigoentidadfederal,localidad);
        final String responsejson = call(GET, url);
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
    public List<Sucursal> precios(String identificador, String codigoentidadfederal, String localidad, List <String> codigos) throws RuntimeException {
        final String query = getQuery(PRECIOS, IDENTIFICADOR, CODIGO_IDENTIDAD_FEDERAL,LOCALIDAD, CODIGOS);
        final String url = String.format(query, identificador, codigoentidadfederal,localidad, codigos.stream().collect(Collectors.joining(",")));
        final String responsejson = call(POST, url);
        //TODO log
        final Response resp = JsonUtils.toObject(responsejson , Response.class);
        if(resp.getCodigo()==0) {
            final Sucursal[] arrpsucs = JsonUtils.toObject(resp.getJson(), Sucursal[].class);
            return Stream.of(arrpsucs).collect(Collectors.toList());
        }
        else {
            throw new RuntimeException(resp.getMensaje());
        }

    }

    @Override
    public List<InfoSucursal> info(String identificador, Long idSucursal) throws RuntimeException {
        final String query = getQuery(INFO, IDENTIFICADOR, IDSUCURSAL);
        final String url = String.format(query, identificador, idSucursal);
        final String responsejson = call(GET, url);
        //TODO log
        final Response resp = JsonUtils.toObject(responsejson , Response.class);
        if(resp.getCodigo()==0) {
            final InfoSucursal[] arrpsucs = JsonUtils.toObject(resp.getJson(), InfoSucursal[].class);
            return Stream.of(arrpsucs).collect(Collectors.toList());
        }
        else {
            throw new RuntimeException(resp.getMensaje());
        }
    }



   /* private void fireAndForget(final String method, final String callTo) throws ClientException {
        try {
            final HttpResponse resp = client().execute(HTTPFactory.apply(method, callTo));

            final int statusCode = resp.getStatusLine().getStatusCode();

            if (statusCode >= 500)
                throw new ClientException("ENDPOINT IS DOWN = " + resp.toString());
            if (statusCode >= 400)
                throw new ClientException("BAD REQUEST = " + resp.toString());

        } catch (final IOException e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }*/

}