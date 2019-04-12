package daos;

import beans.CategoriaProductoBean;
import beans.ProductoBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSProductosDao  extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        ProductoBean producto = new ProductoBean();
        producto.setIdComercial(result.getString("idComercial"));
        producto.setIdCategoria(result.getLong("idCategoria"));
        producto.setNombreCategoria(result.getString("nombreCategoria"));
        producto.setNombre(result.getString("nombre"));
        producto.setNombreMarca(result.getString("nombreMarca"));
        producto.setImagen(result.getString("imagen"));
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
        ProductoBean  producto = (ProductoBean) bean;
        this.connect();
        this.setProcedure("dbo.spProductos(?)");
        if(producto.getIdCategoria() == null) {
            this.setNull(1, Types.BIGINT);
        }
        else {
            this.setParameter(1, producto.getIdCategoria());
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
