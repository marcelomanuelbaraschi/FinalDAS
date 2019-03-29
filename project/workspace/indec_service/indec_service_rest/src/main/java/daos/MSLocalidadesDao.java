package daos;

import beans.LocalidadBean;
import beans.ProductoBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSLocalidadesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        LocalidadBean loc = new LocalidadBean();
        loc.setIdProv(result.getLong("idProvincia"));
        loc.setNombre(result.getString("nombre"));
        return loc;
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
        LocalidadBean loc = (LocalidadBean) bean;
        this.connect();
        this.setProcedure("dbo.spLocalidades(?)");
        if(loc.getIdProv() == null) {
            this.setNull(1, Types.BIGINT);
        }
        else {
            this.setParameter(1, loc.getIdProv());
        }
        List<Bean> localidades = this.executeQuery();
        this.disconnect();
        return localidades;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
