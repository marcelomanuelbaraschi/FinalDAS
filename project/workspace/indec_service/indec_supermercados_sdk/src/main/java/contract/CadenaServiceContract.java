package contract;

import clients.exceptions.ClientException;

public interface CadenaServiceContract {
    String health(String identificador) throws ClientException;
    // deberia ser por un numero de provincia y localidad de conocimiento general
    //String productosPorSucursal (String provincia, String localidad, String [] idsProductos) throws ClientException;
    //String sucursales (String provincia,String localidad) throws ClientException;
}