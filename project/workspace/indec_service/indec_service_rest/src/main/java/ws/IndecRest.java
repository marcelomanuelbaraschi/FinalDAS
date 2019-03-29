package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/app")
public class IndecRest {

    //protected static final Logger log = LoggerFactory.getLogger(IndecRest.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {
        System.out.println("Rest health identificador ->" + identificador);
        return "OK";
    }

    @GET
    @Path("/categorias")
    @Produces(MediaType.APPLICATION_JSON)
    public String categorias (@QueryParam("identificador") final String identificador) {

         System.out.println("Rest categorias identificador ->" + identificador);

         return gson.toJson(getCategorias());
    }

    private List<Categoria> getCategorias(){

        Categoria pan = new Categoria();
        pan.setNombre("Pan");
        pan.setImagen("./../../assets/img/cat_pan.png");

        Categoria galletitasDulces = new Categoria();
        galletitasDulces.setNombre("Galletitas Dulces");
        galletitasDulces.setImagen("./../../assets/img/cat_gal_d.jpg");

        Categoria azucar = new Categoria();
        azucar.setNombre("Galletitas Dulces");
        azucar.setImagen("./../../assets/img/cat_gal_d.jpg");

        Categoria aceite = new Categoria();
        aceite.setNombre("Aceite");
        aceite.setImagen("../../../assets/img/cat_aceite.png");

        Categoria bebidasAlcoholicas = new Categoria();
        bebidasAlcoholicas.setNombre("Bebidas Alcoholicas");
        bebidasAlcoholicas.setImagen("./../../assets/img/cat_beb_a.png");

        List<Categoria> categorias = new LinkedList<Categoria>();

        categorias.add(pan);
        categorias.add(azucar);
        categorias.add(aceite);
        categorias.add(bebidasAlcoholicas);
        return categorias;
    }
}

