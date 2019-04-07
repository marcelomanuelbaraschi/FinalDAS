package daos;

import bean.CriterioBusquedaProductosBean;
import bean.ProductoBean;
import bean.SucursalBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class MSPreciosSucursalesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
      return null;
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
        final CriterioBusquedaProductosBean cs =  (CriterioBusquedaProductosBean) bean;
        List<Bean>  sucursales = new LinkedList<Bean>(); //prestar atencion a esto
        List <ProductoBean> productos;
        ProductoBean producto;
        this.connect();
        this.setProcedure("dbo.SP_GETPRECIOSSUCURSALES(?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

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

        ResultSet result  = this.getStatement().executeQuery();
        result.next();

        while(result.getRow()>0){
            SucursalBean sucursal = new SucursalBean();
            sucursal.setIdSucursal(result.getLong("idSucursal"));
            sucursal.setSucursalNombre(result.getString("sucursalNombre"));
            sucursal.setDireccion(result.getString("direccion"));
            sucursal.setLat(result.getString("lat"));
            sucursal.setLng(result.getString("lng"));
            productos = new LinkedList<ProductoBean>();
            while (result.getRow()>0 && sucursal.getIdSucursal() == result.getLong("idSucursal")){
                producto = new ProductoBean();
                producto.setCodigoProducto(result.getString("codigoProducto"));
                producto.setPrecio(result.getFloat("precio"));
                productos.add(producto);
                result.next();
            }
            sucursal.setProductos(productos);
            sucursales.add(sucursal);
        }
        this.disconnect();
        return sucursales;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
