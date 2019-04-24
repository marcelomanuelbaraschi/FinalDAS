package ws;


import api.CadenaApi;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "CadenaCXFOnePort", serviceName = "CadenaCXFOneService")
public class CadenaCXFOne {


    @WebMethod(operationName = "health", action = "urn:Health")
    public String health() {
        return "OK";
    }


    @WebMethod(operationName = "precios", action = "urn:Precios")
    public String precios(@WebParam(name = "codigoentidadfederal") final String codigoEntidadFederal
                         ,@WebParam(name = "localidad") final String localidad
                         ,@WebParam(name = "codigos") final String codigos) {

        return CadenaApi.getInstance()
                        .preciosSucursales(codigoEntidadFederal, localidad, codigos);
    }

    @WebMethod(operationName = "sucursales", action = "urn:Sucursales")
    public String sucursales(@WebParam(name = "codigoentidadfederal") final String codigoEntidadFederal
                            ,@WebParam(name = "localidad") final String localidad) {

        return CadenaApi.getInstance()
                        .sucursales(codigoEntidadFederal, localidad);
    }

}
