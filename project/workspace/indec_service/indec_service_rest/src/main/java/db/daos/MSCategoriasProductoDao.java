package db.daos;
import beans.CategoriaProducto;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCategoriasProductoDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setId(result.getLong("id"));
        categoria.setNombre(result.getString("nombre"));
        categoria.setUrlImagen(result.getString("urlImagen"));
        return categoria;
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
            this.setProcedure("dbo.spCategoriasProducto");
            List<Bean> categorias = this.executeQuery();
            this.disconnect();
            return categorias;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
