package clients;

import beans.ServiceConfigBean;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import contract.SupermercadosServiceContract;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.JsonUtils;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ConsumeServiceSpec {

    @BeforeClass
    public static void cacheConfigs() throws SQLException {

    }

    @Test
    public void test_all_health(){

        try {
            List<ServiceConfigBean> configs = cadenasServiceConfigs();
            for(ServiceConfigBean config:configs) {
                final Optional<SupermercadosServiceContract> maybeClient =
                        ClientFactory.getInstance().getClientFor(config.getClientType(), config.getParams());
                assertTrue(maybeClient.isPresent());
                final String confirmation = maybeClient.get().health("INDEC");
                System.out.println(confirmation);
            }
            assertTrue(true);
        } catch (ClientException | SQLException e) {
            fail();
        }
    }

    public ServiceConfigBean cadenasServiceConfigsByCadenaId(Long id) throws SQLException {
        Dao daoServiceConfig = DaoFactory.getDaoSimple("ServiceConfigs");
        ServiceConfigBean bean = new ServiceConfigBean();
        bean.setIdCadena(id);
        List<Bean> beans = daoServiceConfig.select(bean);
        final ServiceConfigBean[] serviceConfigBeans = JsonUtils.toObject(JsonUtils.toJsonString(beans), ServiceConfigBean[].class);
        return serviceConfigBeans[0];
    }

    public static List<ServiceConfigBean> cadenasServiceConfigs() throws SQLException {
        Dao daoServiceConfig = DaoFactory.getDaoSimple("ServiceConfigs");
        ServiceConfigBean bean = new ServiceConfigBean();
        List<Bean> beans = daoServiceConfig.select(bean);
        final ServiceConfigBean[] serviceConfigBeans = JsonUtils.toObject(JsonUtils.toJsonString(beans), ServiceConfigBean[].class);
        return Arrays.asList(serviceConfigBeans);

    }
}