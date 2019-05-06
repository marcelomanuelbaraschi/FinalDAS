package daos;

import beans.Cadena;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCadenasDao extends DaoImpl {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Cadena cadena = new Cadena();
        cadena.setId(result.getLong("id"));
        cadena.setNombre(result.getString("nombre"));
        cadena.setImagen(result.getString("imagen"));
        return cadena;
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
        this.setProcedure("dbo.spCadenas");
        List <Bean> cadenas = this.executeQuery();
        this.disconnect();
        return cadenas;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
