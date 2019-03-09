package ws;

import bean.MarcaBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/supermercadoRestOne")
public class SupermercadoRestOne {

    protected static final Logger log = LoggerFactory.getLogger(SupermercadoRestOne.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();


   /* @GET
    @Path("/marcas")
    @Produces(MediaType.APPLICATION_JSON)
    public String marcas(@QueryParam("identificador") final String identificador,
                         @QueryParam("marca") final String marca) throws SQLException {

        log.debug("Rest marcas identificador -> {}", identificador);

        Dao dao = DaoFactory.getDaoSimple("Marcas");
        MarcaBean bean = new MarcaBean();
        bean.setMarca(marca);
        List<Bean> marcas = dao.select(bean);

        return gson.toJson(marcas);
    }*/


    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {

        log.debug("Rest health identificador -> {}", identificador);

        return "OK";
    }
}
