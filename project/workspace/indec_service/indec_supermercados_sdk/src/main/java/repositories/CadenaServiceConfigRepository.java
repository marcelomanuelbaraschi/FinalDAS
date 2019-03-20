package repositories;
import beans.CadenaServiceConfig;
import clients.factory.ClientFactory;
import contract.SupermercadosServiceContract;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CadenaServiceConfigRepository implements ICadenaServiceConfigRepository{

    private static CadenaServiceConfigRepository instance = new CadenaServiceConfigRepository();
    protected static final Logger log = LoggerFactory.getLogger(CadenaServiceConfigRepository.class);

    private CadenaServiceConfigRepository() {
    }

    public static CadenaServiceConfigRepository getInstance() {
        return instance;
    }


    @Override
    public Optional<SupermercadosServiceContract> query(Long cadenaId) {

        try {
            Dao daoServiceConfig = DaoFactory.getDaoSimple("CadenaServiceConfigs");
            CadenaServiceConfig bean = new CadenaServiceConfig();
            bean.setIdCadena(cadenaId);
            List<Bean> beans = daoServiceConfig.select(bean);
            final CadenaServiceConfig[] arrayConfigs = JsonUtils.toObject(JsonUtils.toJsonString(beans), CadenaServiceConfig[].class);
            List<CadenaServiceConfig> listConfig = Arrays.asList(arrayConfigs);

            if (listConfig.isEmpty()){
                return Optional.empty();
            }else{
                CadenaServiceConfig config  = listConfig.get(0);
                ClientFactory.getInstance().getClient(config.getTecnologia(),config.getUrl());
            }

        } catch (SQLException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public Optional<SupermercadosServiceContract> store(CadenaServiceConfig config) {
        return Optional.empty();
    }

    @Override
    public Boolean activate(Long cadenaId) {
        return false;
    }

    @Override
    public Boolean deactivate(Long cadenaId) {
        return false;
    }

    @Override
    public Boolean delete(Long cadenaId) {
        return false;
    }
}

