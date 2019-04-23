package contract;

import cadenasObjects.InfoSucursal;
import cadenasObjects.Sucursal;
import clients.exceptions.ClientException;

import java.util.List;

public interface CadenaServiceContract {
    String health(String identificador) throws ClientException;
    List<Sucursal> sucursales (String identificador, String codigoentidadfederal, String localidad) throws ClientException;
    List<Sucursal> precios (String identificador, String codigoentidadfederal, String localidad,List <String> codigos) throws ClientException;
    List<InfoSucursal> info (String identificador, Long idSucursal) throws ClientException;
}