package ws;

import beans.CategoriaProductoBean;
import beans.ProductoBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import db.Bean;
import db.Dao;
import db.DaoFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
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
        try {
            CategoriaProductoBean categoria = new CategoriaProductoBean();
            Dao dao = DaoFactory.getDao("CategoriasProducto", "");
            List<Bean> configs = dao.select(categoria);
            return gson.toJson(configs);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @GET
    @Path("/productos")
    @Produces(MediaType.APPLICATION_JSON)
    public String productos (@QueryParam("identificador") final String identificador,
                             @QueryParam("idcategoria") final Long idCategoria) {

        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(idCategoria);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            return gson.toJson(productos);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

}

