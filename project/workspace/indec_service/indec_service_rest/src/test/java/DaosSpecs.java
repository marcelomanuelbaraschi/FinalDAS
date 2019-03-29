import beans.CadenaServiceConfigBean;
import beans.CategoriaProductoBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DaosSpecs {

    protected Logger log = LoggerFactory.getLogger(DaosSpecs.class);
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
            log.error(ex.getMessage());
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
            log.error(ex.getMessage());
            fail();
        }
    }

}
