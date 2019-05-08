package daos;
import beans.config_models.Configuracion;
import db.Bean;
import db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConfiguracionesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Configuracion config = new Configuracion();
        config.setId(result.getLong("id"));
        config.setTecnologia(result.getString("tecnologia"));
        config.setIdCadena(result.getLong("idCadena"));
        config.setNombreCadena(result.getString("nombreCadena"));
        config.setUrl(result.getString("url"));
        return config;
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
        this.connect();
        this.setProcedure("dbo.spCadenasServicesConfigs");
        List<Bean> configs = this.executeQuery();
        this.disconnect();
        return configs;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }

}
