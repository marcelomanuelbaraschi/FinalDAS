import beans.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DaosSpecs {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @Test
    public void MSCadenasServicesConfigsDao_success() {
        try {
            CadenaServiceConfigBean config = new CadenaServiceConfigBean();
            Dao dao = DaoFactory.getDao("CadenasServicesConfigs", "");
            List<Bean> configs = dao.select(config);
            assertEquals(configs.size(),5);
            System.out.println(gson.toJson(configs));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSCategoriasProductoDao_success() {
        try {
            CategoriaProductoBean categoria = new CategoriaProductoBean();
            Dao dao = DaoFactory.getDao("CategoriasProducto", "");
            List<Bean> configs = dao.select(categoria);
            assertEquals(configs.size(),30);
            System.out.println(gson.toJson(configs));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSProductosDao_success() {
        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(1L);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            System.out.println(gson.toJson(productos));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSProvinciasDao_success() {
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            System.out.println(gson.toJson(provincias));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSLocalidadesDao_success() {
        try {
            LocalidadBean localidad = new LocalidadBean();
            localidad.setIdProv(2L);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            System.out.println(gson.toJson(localidades));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSLocalidadesDao_success_null_case() {
        try {
            LocalidadBean localidad = new LocalidadBean();
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            System.out.println(gson.toJson(localidades));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

}
