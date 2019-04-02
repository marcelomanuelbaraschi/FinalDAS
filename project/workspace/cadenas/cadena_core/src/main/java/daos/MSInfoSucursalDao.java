package daos;

import bean.CriterioInfoBean;
import bean.CriterioLocalizacionBean;
import bean.InfoSucursalBean;
import bean.SucursalBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSInfoSucursalDao  extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        InfoSucursalBean is = new InfoSucursalBean();
        is.setDireccion(result.getString("direccion"));
        is.setLat(result.getString("lat"));
        is.setLng(result.getString("lng"));
        is.setSucursalNombre(result.getString("sucursalNombre"));
        is.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));
        return is;
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
        final CriterioInfoBean criterio = (CriterioInfoBean) bean;
        List<Bean>  suc;
        this.connect();
        this.setProcedure("dbo.SP_GETINFOSUCURSAL(?)");
        if(criterio.getIdSucursal() == null) {
            this.setNull(1, Types.BIGINT);
        }
        else {
            this.setParameter(1, criterio.getIdSucursal());
        }
        suc = this.executeQuery();
        this.disconnect();
        return suc;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
