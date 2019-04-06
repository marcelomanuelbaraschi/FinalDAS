package contract;

import cadenasObjects.InfoSucursal;
import cadenasObjects.PreciosSucursal;
import cadenasObjects.Sucursal;

import java.util.List;

public interface CadenaServiceContract {
    String health(String identificador) throws RuntimeException;
    List<Sucursal> sucursales (String identificador, String codigoentidadfederal, String localidad) throws RuntimeException;
    List<PreciosSucursal> precios (String identificador, String codigoentidadfederal, String localidad,List <String> codigos) throws RuntimeException;
    List<InfoSucursal> info (String identificador, Long idSucursal) throws RuntimeException;
}