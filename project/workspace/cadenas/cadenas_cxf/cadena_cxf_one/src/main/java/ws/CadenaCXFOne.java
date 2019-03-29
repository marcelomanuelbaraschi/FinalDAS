package ws;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "CadenaCXFOnePort", serviceName = "CadenaCXFOneService")
public class CadenaCXFOne {
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();


    @WebMethod(operationName = "health", action = "urn:Health")
    public String health(@WebParam(name = "identificador") final String identificador) {
        System.out.println("Cxf health identificador -> " + identificador);
        return "OK";
    }

}
