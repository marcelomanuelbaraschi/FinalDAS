package daos;
import beans.CadenaServiceConfig;
import clients.Tecnologia;
import db.Bean;
import db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MSCadenaServiceConfigsDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        return  null;
    }

    @Override
    public void insert(Bean bean) throws SQLException {

    }

    @Override
    public void update(Bean bean) throws SQLException {

    }

    @Override
    public void delete(Bean bean) throws SQLException {

    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        List<Bean> configs;
        configs = new LinkedList<>();
        CadenaServiceConfig cadenaConfig = new CadenaServiceConfig();
        cadenaConfig.setIdCadena(1L);
        cadenaConfig.setNombreCadena("Wallmart");
        cadenaConfig.setTecnologia(Tecnologia.SOAP);
        cadenaConfig.setUrl("http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl");
        configs.add(cadenaConfig);
        return configs;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }

    /*@Override
    public List<Bean> select(Bean bean) throws SQLException {
        CadenaServiceConfigBean cadenaServiceConfigParam = (CadenaServiceConfigBean) bean;
        List<Bean> cadenaServiceConfigs;
        this.connect();
        this.setProcedure("dbo.get_cadena_service_configs(?)");
        if(cadenaServiceConfigParam.getIdCadena() != null) {
            this.setParameter(1, cadenaServiceConfigParam.getIdCadena());
        }
        else{
            this.setNull(1, Types.BIGINT);
        }
        cadenaServiceConfigs = this.executeQuery();
        this.disconnect();
        return cadenaServiceConfigs;
    }*/
}
