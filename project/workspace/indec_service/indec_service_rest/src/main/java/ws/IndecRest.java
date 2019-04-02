package ws;


import db.Bean;
import db.Dao;
import db.DaoFactory;

import beans.CategoriaProductoBean;
import beans.LocalidadBean;
import beans.ProductoBean;
import beans.ProvinciaBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
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
            List<Bean> categorias = dao.select(categoria);
            return gson.toJson(categorias);
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

    @GET
    @Path("/provincias")
    @Produces(MediaType.APPLICATION_JSON)
    public String provincias (@QueryParam("identificador") final String identificador) {
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            return (gson.toJson(provincias));
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @GET
    @Path("/localidades")
    @Produces(MediaType.APPLICATION_JSON)
    public String localidades (@QueryParam("identificador") final String identificador,
                               @QueryParam("idprovincia") final Long idProvincia) {
        try {
            LocalidadBean localidad = new LocalidadBean();
            localidad.setIdProv(idProvincia);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            return (gson.toJson(localidades));
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }


}

