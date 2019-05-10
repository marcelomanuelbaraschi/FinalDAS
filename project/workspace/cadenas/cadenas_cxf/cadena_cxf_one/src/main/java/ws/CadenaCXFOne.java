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

        if(codigoentidadfederal == null) throw new Exception("El codigoentidadfederal codigos es null.");
        if(localidad == null) throw new Exception("El parametro localidad es null.");

        final List<Sucursal> sucs =
                CadenaAPI.sucursales(codigoentidadfederal, localidad);
        return GSON.toJson(sucs);
    }

    @WebMethod(operationName = "precios", action = "urn:Precios")
    public String precios(@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
                         ,@WebParam(name = "localidad") final String localidad
                         ,@WebParam(name = "codigos") final String codigos) throws Exception{

        if(codigoentidadfederal == null) throw new Exception("El codigoentidadfederal codigos es null.");
        if(localidad == null) throw new Exception("El parametro localidad es null.");
        if(codigos == null) throw new Exception("El parametro codigos es null.");

        final List<Sucursal> ps =
                CadenaAPI.preciosSucursales(codigoentidadfederal,localidad,codigos);
        return GSON.toJson(ps);
    }

}
