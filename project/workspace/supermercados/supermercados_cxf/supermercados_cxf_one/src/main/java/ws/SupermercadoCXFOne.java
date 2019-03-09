package ws;

import bean.MarcaBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "SupermercadoCXFOnePort", serviceName = "SupermercadoCXFOneService")
public class SupermercadoCXFOne {
    protected static final Logger log = LoggerFactory.getLogger(SupermercadoCXFOne.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();


    @WebMethod(operationName = "health", action = "urn:Health")
    public String health(@WebParam(name = "identificador") final String identificador) {
        log.debug("Cxf health identificador -> " + identificador);
        return "OK";
    }

    @WebMethod(operationName = "marcas", action = "urn:Marcas")
    public String marcas(@WebParam(name = "identificador") final String identificador,
                         @WebParam(name = "marca") final String marca) throws SQLException {
        log.debug("Cxf marcas identificador -> {} - marca -> {}", identificador, marca);
        Dao dao = DaoFactory.getDaoSimple("Marcas");
        MarcaBean bean = new MarcaBean();
        bean.setMarca(marca);
        List<Bean> marcas = dao.select(bean);

        return gson.toJson(marcas);
    }
}
