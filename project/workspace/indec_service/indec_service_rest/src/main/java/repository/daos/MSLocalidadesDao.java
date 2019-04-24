package repository.daos;

import beans.LocalidadBean;
import repository.db.Bean;
import repository.db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSLocalidadesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        LocalidadBean loc = new LocalidadBean();
        loc.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));
        loc.setNombreLocalidad(result.getString("nombreLocalidad"));
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
        if(loc.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, loc.getCodigoEntidadFederal());
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
