package service.Actions;

import beans.Cadena;
import clients.Tecnologia;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ObtenerSucursalesPorCadena {

    public static List<Cadena>  execute (final String codigoentidadfederal, final String localidad) {
        return ObtenerConfiguraciones.execute()
                        .stream().parallel()
                        .map((config) -> ObtenerSucursalesDeCadena.execute(codigoentidadfederal,localidad,config))
                        .collect(toList());
    }
}
