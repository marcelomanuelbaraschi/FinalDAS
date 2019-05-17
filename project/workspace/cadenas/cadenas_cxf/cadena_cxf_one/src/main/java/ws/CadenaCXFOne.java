package ws;


import service.CadenaAPI;
import bean.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/"
           ,portName = "CadenaCXFOnePort"
           ,serviceName = "CadenaCXFOneService")

public class CadenaCXFOne {

    private static final Logger logger = LoggerFactory.getLogger(CadenaCXFOne.class);

    @WebMethod(operationName = "health", action = "urn:Health")
    public String health() {
        return "OK";
    }


    @WebMethod(operationName = "sucursales", action = "urn:Sucursales")
    public String sucursales(@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
                            ,@WebParam(name = "localidad") final String localidad) throws Exception {


        return CadenaAPI.sucursales(codigoentidadfederal, localidad);
    }

    @WebMethod(operationName = "precios", action = "urn:Precios")
    public String precios(@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
                         ,@WebParam(name = "localidad") final String localidad
                         ,@WebParam(name = "codigos") final String codigos) throws Exception{

        return CadenaAPI.preciosSucursales(codigoentidadfederal,localidad,codigos);
    }

}
