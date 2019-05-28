package db.daos;

import db.beans.CriterioBusquedaProducto;
import db.beans.Producto;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSProductosDao  extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigoDeBarras(result.getString("codigoDeBarras"));
        producto.setIdCategoria(result.getLong("idCategoria"));
        producto.setNombreCategoria(result.getString("nombreCategoria"));
        producto.setNombreProducto(result.getString("nombreProducto"));
        producto.setNombreMarca(result.getString("nombreMarca"));
        producto.setImagenProducto(result.getString("imagenProducto"));
        return producto;
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
        CriterioBusquedaProducto criterio = (CriterioBusquedaProducto) bean;
        this.connect();
        this.setProcedure("dbo.spProductos(?,?,?)");

        if(criterio !=null){
            if(criterio.getIdCategoria()!=null){
                this.setParameter(1,criterio.getIdCategoria());
            }else{
                this.setNull(1,Types.SMALLINT);
            }
            if(criterio.getCodigos()!=null){
                this.setParameter(2,criterio.getCodigos());
            }else{
                this.setNull(2,Types.VARCHAR);
            }
            if(criterio.getKeyword()!=null){
                this.setParameter(3,criterio.getKeyword());
            }else{
                this.setNull(3,Types.VARCHAR);
            }
        }else{
            this.setNull(1,Types.SMALLINT);
            this.setNull(2,Types.VARCHAR);
            this.setNull(3,Types.VARCHAR);
        }

        List<Bean> productos = this.executeQuery();
        this.disconnect();
        return productos;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
