package contract;

import clients.exceptions.ClientException;

public interface CadenaServiceContract {
    String health(String identificador) throws ClientException;
   // String productosPorSucursal (String provincia, String localidad, String [] idsProductos) throws ClientException;
   // String sucursales (String provincia,String localidad) throws ClientException;
}