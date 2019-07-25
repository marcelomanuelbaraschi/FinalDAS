package contract;

import clients.exceptions.ClientException;
import ds.Sucursal;

import java.util.List;

public  interface  CadenaServiceContract{

     String health() throws ClientException;

     List<Sucursal> sucursales (String codigoentidadfederal, String localidad) throws ClientException;

     List<Sucursal> preciosSucursales (String codigoentidadfederal, String localidad, String codigos)  throws ClientException;
}


