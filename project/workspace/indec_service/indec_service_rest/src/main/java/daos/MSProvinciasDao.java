package daos;

import beans.ProductoBean;
import beans.ProvinciaBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSProvinciasDao extends DaoImpl {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        ProvinciaBean prov = new ProvinciaBean();
        prov.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));
        prov.setNombreProvincia(result.getString("nombreProvincia"));
        return prov;
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
        this.setProcedure("dbo.spProvincias");
        List<Bean> provincias = this.executeQuery();
        this.disconnect();
        return provincias;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
