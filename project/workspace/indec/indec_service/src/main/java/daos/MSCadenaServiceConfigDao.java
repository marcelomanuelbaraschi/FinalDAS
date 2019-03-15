package daos;
import beans.CadenaServiceConfigBean;
import db.Bean;
import db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCadenaServiceConfigDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Long   idCadena = result.getLong(1);
        String nombreCadena = result.getString(2);
        String nombreParametro = result.getString(3);
        String tecnologia = result.getString(4);
        String valor = result.getString(5);
        CadenaServiceConfigBean bean = new CadenaServiceConfigBean();
        bean.setIdCadena(idCadena);
        bean.setNombreCadena(nombreCadena);
        bean.setNombreParametro(nombreParametro);
        bean.setTecnologia(tecnologia);
        bean.setValor(valor);
        return bean;
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
        CadenaServiceConfigBean cadenaServiceConfigParam = (CadenaServiceConfigBean) bean;
        List<Bean> cadenaServiceConfigs;
        this.connect();
        this.setProcedure("dbo.get_cadena_service_config(?)");
        if(cadenaServiceConfigParam.getIdCadena() != null) {
            this.setParameter(1, cadenaServiceConfigParam.getIdCadena());
        }
        cadenaServiceConfigs = this.executeQuery();
        this.disconnect();
        return cadenaServiceConfigs;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
