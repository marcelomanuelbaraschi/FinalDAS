package service.Actions;

import beans.Cadena;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ObtenerPreciosPorCadena {

    public static List<Cadena> execute (final String codigoentidadfederal, final String localidad, final String codigos){

        return ObtenerConfiguraciones.execute()
        .parallelStream()
        .map((config) -> ObtenerPreciosDeCadena.execute(codigoentidadfederal,localidad,codigos , config))
        .collect(toList());

    }

}
