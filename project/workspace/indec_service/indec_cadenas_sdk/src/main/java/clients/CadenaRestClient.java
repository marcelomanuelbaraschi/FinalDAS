package clients;

import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;
import utils.JsonMarshaller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.*;

public class CadenaRestClient extends RestClient implements CadenaServiceContract {

    public CadenaRestClient(final String url){
        super(url);
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

        //Validamos parametros.
        if(codigoentidadfederal == null) throw new ClientException("El parametro codigos es null.");
        if(localidad == null) throw new ClientException("El parametro codigos es null.");

        final String query = getQuery(SUCURSALES,CODIGO_ENTIDAD_FEDERAL,LOCALIDAD);
        final String url = String.format(query,codigoentidadfederal,localidad);
        final String sucursalesJson = call(GET, url);
        final Sucursal[] arrsucs = JsonMarshaller.toObject(sucursalesJson , Sucursal[].class);
        return Stream.of(arrsucs).collect(Collectors.toList());
    }

    @Override
    public List<Sucursal> precios(String codigoentidadfederal, String localidad, List<String> codigos)
            throws ClientException
    {

        //Validamos parametros.
        if(codigos == null) throw new ClientException("El parametro codigos es null.");
        if(codigoentidadfederal == null) throw new ClientException("El parametro codigos es null.");
        if(localidad == null) throw new ClientException("El parametro codigos es null.");

        String strcodigos;
        try {
            strcodigos = codigos.stream().collect(Collectors.joining(","));
        }catch(NullPointerException e) {
            throw new ClientException("El parametro codigo es null");
        }

        final String query = getQuery(PRECIOS,CODIGO_ENTIDAD_FEDERAL, LOCALIDAD, CODIGOS);
        final String url = String.format(query,codigoentidadfederal, localidad, strcodigos);
        final String preciosJson = call(POST, url);
        final Sucursal[] arrpsucs = JsonMarshaller.toObject(preciosJson, Sucursal[].class);
        return Stream.of(arrpsucs).collect(Collectors.toList());
    }
}
