package ws;


import bean.Sucursal;
import service.CadenaAPI;
import utilities.GSON;

import java.util.List;

public class CadenaAxisOne {

    public String health() {
        return "OK";
    }


    public String sucursales (final String codigoentidadfederal
                             ,final String localidad) throws Exception {

        return CadenaAPI.sucursales(codigoentidadfederal, localidad);
    }

    public String precios(final String codigoentidadfederal
                         ,final String localidad
                         ,final String codigos) throws Exception {

        return CadenaAPI.preciosSucursales(codigoentidadfederal,localidad,codigos);

    }
}
