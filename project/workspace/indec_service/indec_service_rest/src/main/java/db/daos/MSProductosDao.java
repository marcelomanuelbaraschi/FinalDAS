package db.daos;

import beans.Producto;
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
        this.connect();
        this.setProcedure("dbo.spProductos");
        List<Bean> productos = this.executeQuery();
        this.disconnect();
        return productos;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
