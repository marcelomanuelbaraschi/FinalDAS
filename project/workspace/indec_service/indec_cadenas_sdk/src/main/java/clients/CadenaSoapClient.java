package clients;

import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;
import contract.CadenaServiceContract;
import utils.JsonMarshaller;
import java.util.List;
import java.util.stream.Stream;

import static constants.Constants.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CadenaSoapClient extends SoapClient implements CadenaServiceContract {

    public CadenaSoapClient(final String wsdlUrl) {
         super(wsdlUrl);
     }

     public String health()
            throws ClientException
    {
        final Object object = executeMethod(HEALTH);
        final String jsonBean = object.toString();
        return jsonBean;
    }


    public List<Sucursal> sucursales
            (final String codigoentidadfederal
            ,final String localidad)
            throws ClientException
    {
        final Object object = executeMethod(SUCURSALES,codigoentidadfederal,localidad);
        final String sucursalesJson = object.toString();
        final Sucursal[] arrsucs = JsonMarshaller.toObject(sucursalesJson , Sucursal[].class);
        return Stream.of(arrsucs).collect(toList());
    }


    public List<Sucursal> precios
            (final String codigoentidadfederal,
             final String localidad, List<String> codigos)
            throws ClientException
    {
        final String strCodigos = codigos.stream().collect(joining(","));
        final Object object = executeMethod(PRECIOS,codigoentidadfederal,localidad,strCodigos);
        final String preciosJson = object.toString();
        final Sucursal[] arrpsucs = JsonMarshaller.toObject(preciosJson, Sucursal[].class);
        return Stream.of(arrpsucs).collect(toList());

    }


}