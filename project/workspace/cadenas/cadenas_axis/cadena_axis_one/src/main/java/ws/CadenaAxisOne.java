package ws;

import api.CadenaApi;

public class CadenaAxisOne {

    public String health() {

        return "OK";
    }

    public String sucursales (final String codigoentidadfederal
                             ,final String localidad)  {

        return CadenaApi.getInstance()
                        .sucursales(codigoentidadfederal, localidad);
    }

    public String precios(final String codigoentidadfederal
                         ,final String localidad
                         ,final String codigos) {

        return  CadenaApi.getInstance()
                         .preciosSucursales(codigoentidadfederal, localidad, codigos);
    }
}
