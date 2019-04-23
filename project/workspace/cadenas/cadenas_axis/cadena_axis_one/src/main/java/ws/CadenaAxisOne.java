package ws;

import endpoint.CadenaEndpoint;

public class CadenaAxisOne {

    public String health(final String identificador) {

        return "OK";
    }

    public String sucursales (final String identificador
            ,final String codigoentidadfederal
            ,final String localidad)  {

        return CadenaEndpoint.getInstance().sucursales(codigoentidadfederal, localidad);
    }


    public String info ( final String identificador, final Long idsucursal ) {

        return  CadenaEndpoint.getInstance().infoSucursales(idsucursal);
    }

    public String precios ( final String identificador
                            ,final String codigoentidadfederal
                            ,final String localidad
                            ,final String codigos) {

        return  CadenaEndpoint.getInstance().preciosSucursales(codigoentidadfederal, localidad, codigos);
    }
}
