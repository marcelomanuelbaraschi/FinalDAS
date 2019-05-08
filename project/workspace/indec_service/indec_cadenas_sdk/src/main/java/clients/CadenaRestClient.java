package clients;

import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;
import utils.GSON;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;

public class CadenaRestClient extends RestClient implements CadenaServiceContract {

    private final Long idCadena;

    public CadenaRestClient(final String url,final Long idCadena){
        super(url);
        this.idCadena = idCadena;
    }

    @Override
    public String health() throws ClientException {
        final String url = getQuery(HEALTH);
        //TODO log
        return call(GET, String.format(url));
    }

    @Override
    public List<Sucursal> sucursales(final String codigoentidadfederal, final String localidad)
            throws ClientException
    {

        //Contruimos el formato de la query string.
        final String query = getQuery(SUCURSALES,CODIGO_ENTIDAD_FEDERAL,LOCALIDAD);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal,localidad);

        //Hacemos la llamada http
        final String sucursalesJson = call(GET, url);

        //Pasamos de un json a una Lista de Sucursales
        List<Sucursal> sucursales = Arrays.asList(GSON.toObject(sucursalesJson, Sucursal[].class));

        //Le asignamos a las sucursales un idCadena
        if (!sucursales.isEmpty()) {

            for (Sucursal sucursal : sucursales) {
                sucursal.setIdCadena(this.idCadena);
            }
            return sucursales;
        }
        else throw new ClientException("La lista de Sucursales de la Cadena " + idCadena +  " esta vacia");
    }

    @Override
    public List<Sucursal> precios(String codigoentidadfederal, String localidad, List<String> codigos)
            throws ClientException
    {

        //Validamos parametros.
        if(codigos == null) throw new ClientException("El parametro codigos es null.");

        //Pasamos de una lista a una string separado por coma.
        String strcodigos = codigos.stream().collect(Collectors.joining(","));

        //Contruimos el formato de la query string
        final String query = getQuery(PRECIOS,CODIGO_ENTIDAD_FEDERAL, LOCALIDAD, CODIGOS);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal, localidad, strcodigos);

        //Hacemos la llamada http
        final String preciosJson = call(POST, url);

        //Pasamos de un json a una Lista de Sucursales
        List<Sucursal> sucursales = Arrays.asList(GSON.toObject(preciosJson, Sucursal[].class));

        //Le asignamos a las sucursales un  idCadena
        if (!sucursales.isEmpty()) {

            for (Sucursal sucursal : sucursales) {
                sucursal.setIdCadena(this.idCadena);
            }
            return sucursales;
        }
        else throw new ClientException("La lista de Sucursales de la Cadena " + idCadena +  " esta vacia");

    }
}
