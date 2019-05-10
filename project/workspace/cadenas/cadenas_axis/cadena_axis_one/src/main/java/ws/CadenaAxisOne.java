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

        if(codigoentidadfederal == null) throw new Exception("El codigoentidadfederal codigos es null.");
        if(localidad == null) throw new Exception("El parametro localidad es null.");

        final List<Sucursal> sucs =
                CadenaAPI.sucursales(codigoentidadfederal, localidad);

        return GSON.toJson(sucs);
    }

    public String precios(final String codigoentidadfederal
                         ,final String localidad
                         ,final String codigos) throws Exception {

        if(codigoentidadfederal == null) throw new Exception("El codigoentidadfederal codigos es null.");
        if(localidad == null) throw new Exception("El parametro localidad es null.");
        if(codigos == null) throw new Exception("El parametro codigos es null.");

        final List<Sucursal> ps =
                CadenaAPI.preciosSucursales(codigoentidadfederal,localidad,codigos);

        return GSON.toJson(ps);
    }
}
