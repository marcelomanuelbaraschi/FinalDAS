package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/supermercadoRestTwo")
public class SupermercadoRestTwo {

    protected static final Logger log = LoggerFactory.getLogger(SupermercadoRestTwo.class);

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
