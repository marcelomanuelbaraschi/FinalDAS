package contract;

import clients.exceptions.ClientException;

public  interface  CadenaServiceContract{

     String health() throws ClientException;

     String sucursales (String codigoentidadfederal, String localidad) throws ClientException;

     String preciosSucursales (String codigoentidadfederal, String localidad, String codigos)  throws ClientException;
}