package ws;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import endpoint.CadenaEndpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "CadenaCXFOnePort", serviceName = "CadenaCXFOneService")
public class CadenaCXFOne {
    private CadenaEndpoint ce = new CadenaEndpoint();

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();


    @WebMethod(operationName = "health", action = "urn:Health")
    public String health(@WebParam(name = "identificador") final String identificador) {
        return "OK";
    }

    @WebMethod(operationName = "info", action = "urn:Info")
    public String info(@WebParam(name = "identificador") final String identificador,
                       @WebParam(name = "idsucursal") final Long idSucursal) {

        return ce.infoSucursales(idSucursal);
    }

    @WebMethod(operationName = "precios", action = "urn:Precios")
    public String precios(@WebParam(name = "identificador") final String identificador
                          ,@WebParam(name = "codigoentidadfederal") final String codigoEntidadFederal
                          ,@WebParam(name = "localidad") final String localidad
                          ,@WebParam(name = "codigos") final String codigos) {

        return ce.preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }

    @WebMethod(operationName = "sucursales", action = "urn:Sucursales")
    public String sucursales(@WebParam(name = "identificador") final String identificador
            ,@WebParam(name = "codigoentidadfederal") final String codigoEntidadFederal
            ,@WebParam(name = "localidad") final String localidad) {

        return ce.sucursales(codigoEntidadFederal, localidad);
    }

}
