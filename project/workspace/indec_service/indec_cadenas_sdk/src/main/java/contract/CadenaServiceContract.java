package contract;

import beans.Sucursal;
import clients.exceptions.ClientException;

import java.util.List;

public interface CadenaServiceContract {
    String health(String identificador) throws ClientException;
    List<Sucursal> sucursales (String identificadoR, String codigoentidadfederal, String localidad) throws ClientException;
}