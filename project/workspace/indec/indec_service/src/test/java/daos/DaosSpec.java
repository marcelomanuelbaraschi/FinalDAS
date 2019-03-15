package daos;
import beans.CadenaBean;
import beans.CadenaServiceConfigBean;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void test_dao_cadenas_success() {
        try {
            Dao dao = DaoFactory.getDaoSimple("Cadenas");
            assertNotNull(dao);
            CadenaBean bean = new CadenaBean();
            List<Bean> cadenas = dao.select(bean);
            assertNotNull(cadenas);
            List<CadenaBean> expectedCadenas = new ArrayList<CadenaBean>();// we need to append to the end
            CadenaBean cadena1 = new CadenaBean();
            cadena1.setId(1L);
            cadena1.setNombre("Walmart");
            cadena1.setTecnologia("AXIS");
            CadenaBean cadena2 = new CadenaBean();
            cadena2.setId(2L);
            cadena2.setNombre("Jumbo");
            cadena2.setTecnologia("CXF");
            CadenaBean cadena3 = new CadenaBean();
            cadena3.setId(3L);
            cadena3.setNombre("Carrefour");
            cadena3.setTecnologia("REST");
            expectedCadenas.add(cadena1);
            expectedCadenas.add(cadena2);
            expectedCadenas.add(cadena3);
            assertEquals(gson.toJson(expectedCadenas),gson.toJson(cadenas));
        } catch (SQLException e) {
            fail();
        }

    }

    @Test
    public void test_dao_cadena_service_config_success() {
        try {
            Dao dao = DaoFactory.getDaoSimple("CadenaServiceConfig");
            assertNotNull(dao);
            CadenaServiceConfigBean bean = new CadenaServiceConfigBean();
            bean.setIdCadena(1L);
            List<Bean> configs = dao.select(bean);
            assertNotNull(configs);
            List<CadenaServiceConfigBean> expectedConfigs = new ArrayList();
            CadenaServiceConfigBean conf1 = new CadenaServiceConfigBean();
            conf1.setIdCadena(1L);
            conf1.setNombreCadena("Walmart");
            conf1.setNombreParametro("endpointUrl");
            conf1.setTecnologia("AXIS");
            conf1.setValor("http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/");
            CadenaServiceConfigBean conf2 = new CadenaServiceConfigBean();
            conf2.setIdCadena(1L);
            conf2.setNombreCadena("Walmart");
            conf2.setNombreParametro("targetNameSpace");
            conf2.setTecnologia("AXIS");
            conf2.setValor("http://ws.SupermercadoAxisOne/");
            expectedConfigs.add(conf1);
            expectedConfigs.add(conf2);
            assertEquals(gson.toJson(expectedConfigs),gson.toJson(configs));
        } catch (SQLException e) {
            fail();
        }

    }
}
