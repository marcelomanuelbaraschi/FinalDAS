package daos;
import beans.CadenaServiceConfig;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;


public class DaosSpec {
    private Gson gson;

    @Before
    public void initialize_gson(){
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .create();
    }

    @Test
    public void test_dao_cadena_service_configs_success() {
        try {
            Dao daoCadenaServiceConfig = DaoFactory.getDaoSimple("CadenaServiceConfigs");
            assertNotNull(daoCadenaServiceConfig);
            List<Bean> expectedConfigs = daoCadenaServiceConfig.select(new CadenaServiceConfig());
            System.out.println(gson.toJson(expectedConfigs));
            assertTrue(true);
        } catch (SQLException e) {
            fail();
        }
    }


}
