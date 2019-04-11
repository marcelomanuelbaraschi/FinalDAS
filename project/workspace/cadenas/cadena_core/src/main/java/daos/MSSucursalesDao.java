package daos;

import bean.CriterioLocalizacionSucursalBean;
import bean.SucursalBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSSucursalesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        SucursalBean suc = new SucursalBean();
        suc.setDireccion(result.getString("direccion"));
        suc.setIdSucursal(result.getLong("idSucursal"));
        suc.setLat(result.getString("lat"));
        suc.setLng(result.getString("lng"));
        suc.setNombreSucursal(result.getString("nombreSucursal"));
        return suc;
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
        final CriterioLocalizacionSucursalBean criterio = (CriterioLocalizacionSucursalBean) bean;
        List<Bean>  sucs;
        this.connect();
        this.setProcedure("dbo.SP_GETSUCURSALES(?,?)");
        if(criterio.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, criterio.getCodigoEntidadFederal());
        }
        if(criterio.getLocalidad() == null) {
            this.setNull(2, Types.VARCHAR);
        }
        else {
            this.setParameter(2, criterio.getLocalidad());
        }
        sucs = this.executeQuery();
        this.disconnect();
        return sucs;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
