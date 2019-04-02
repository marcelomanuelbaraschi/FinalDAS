package daos;

import bean.CriterioSeleccionBean;
import bean.PreciosSucursalesBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSPreciosSucursalesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        PreciosSucursalesBean ps = new PreciosSucursalesBean();
        ps.setIdSucursal(result.getLong("idSucursal"));
        ps.setCodigoProducto(result.getString("codigoProducto"));
        ps.setPrecio(result.getFloat("precio"));
        return ps;
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
        final CriterioSeleccionBean cs =  (CriterioSeleccionBean) bean;
        List<Bean>  sucs;
        this.connect();
        this.setProcedure("dbo.SP_GETPRECIOSSUCURSAL(?,?,?)");

        if(cs.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, cs.getCodigoEntidadFederal());
        }
        if(cs.getLocalidad() == null) {
            this.setNull(2, Types.VARCHAR);
        }
        else {
            this.setParameter(2, cs.getLocalidad());
        }
        if(cs.getCodigos() == null) {
            this.setNull(3, Types.VARCHAR);
        }
        else {
            this.setParameter(3, cs.getCodigos());
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
