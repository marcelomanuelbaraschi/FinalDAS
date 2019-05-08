package daos;

import beans.in_models.CriterioBusquedaLocalidad;
import beans.out_models.Localidad;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSLocalidadesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Localidad loc = new Localidad();
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
        CriterioBusquedaLocalidad criterio = (CriterioBusquedaLocalidad) bean;
        this.connect();
        this.setProcedure("dbo.spLocalidades(?)");
        if(criterio.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, criterio.getCodigoEntidadFederal());
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
