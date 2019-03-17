package repositories;
import beans.CadenaServiceConfig;
import clients.RestClient;
import clients.SoapClient;
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
import static clients.ClientType.*;
import static java.util.stream.Collectors.toList;

public class CadenaServiceRepository implements ICadenaServiceRepository{

    private static CadenaServiceRepository instance = new CadenaServiceRepository();
    protected static final Logger log = LoggerFactory.getLogger(CadenaServiceRepository.class);

    private CadenaServiceRepository() {
    }

    public static CadenaServiceRepository getInstance() {
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
            List<CadenaServiceConfig> listConfig = Arrays.asList(arrayConfigs)
                                                         .stream()
                                                         .filter(conf -> conf.getIdCadena().equals(cadenaId))
                                                         .collect(toList());
            if (listConfig.isEmpty()){
                return Optional.empty();
            }else{
                CadenaServiceConfig config  = listConfig.get(0);
                if(config.getClientType().equals(SOAP)){
                    return SoapClient.create(config.getParamValue());
                }
                else if (config.getClientType().equals(REST)){
                    return RestClient.create(config.getParamValue());
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }
}

