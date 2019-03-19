package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/indecRest")
public class IndecRest {

   //protected static final Logger log = LoggerFactory.getLogger(IndecRest.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {

        //log.debug("Rest health identificador -> {}", identificador);

        return "OK";
    }
}
