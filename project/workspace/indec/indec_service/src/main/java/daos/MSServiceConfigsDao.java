package daos;
import beans.ServiceConfigBean;
import clients.factory.ClientType;
import db.Bean;
import db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MSServiceConfigsDao extends DaoImpl {
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
    public List<Bean> select(Bean bean) throws SQLException { // falta jugar con el parametro
        ServiceConfigBean paramBean = (ServiceConfigBean) bean;
        List<Bean> configs = new LinkedList<Bean>();
        this.connect();
        this.setProcedure("dbo.get_cadena_service_configs(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if (paramBean.getIdCadena()!=null){
            this.setParameter(1,paramBean.getIdCadena());
        }
        else {
            this.setNull(1,Types.BIGINT);
        }
        this.setNull(1, Types.BIGINT);
        ResultSet result = this.getStatement().executeQuery();
        result.next();
        while(result.getRow() > 0) {
            ServiceConfigBean config = new ServiceConfigBean();
            config.setIdCadena(result.getLong("idCadena"));
            config.setNombreCadena(result.getString("nombreCadena"));
            String clientType = result.getString("tecnologia");

            if(clientType.equals("REST")){
                config.setClientType(ClientType.REST);
            }
            if(clientType.equals("AXIS")){
                config.setClientType(ClientType.AXIS);
            }
            if(clientType.equals("CXF")){
                config.setClientType(ClientType.CXF);
            }

            HashMap<String, String> params = new HashMap<String, String>();
            Long idCadena = config.getIdCadena();
            while(result.getRow() > 0 && idCadena == result.getLong("idCadena")) {
                String nombreParametro = result.getString("nombreParametro");
                String valor = result.getString("valor");
                params.put(nombreParametro,valor);
                result.next();
            }
            config.setParams(params);
            configs.add(config);

        }
        this.disconnect();
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
