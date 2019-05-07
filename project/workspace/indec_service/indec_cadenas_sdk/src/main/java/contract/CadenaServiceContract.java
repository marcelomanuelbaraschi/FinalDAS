package contract;

import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;

import java.util.List;

public  interface  CadenaServiceContract{
     String health
            ()
            throws ClientException;

    List<Sucursal> sucursales
            (String codigoentidadfederal, String localidad)
            throws ClientException;

    List<Sucursal> precios
            (String codigoentidadfederal, String localidad, List<String> codigos)
            throws ClientException;
}