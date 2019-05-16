package db.daos;

import beans.CadenaBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCadenasDao extends DaoImpl {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        CadenaBean cadena = new CadenaBean();
        cadena.setIdCadena(result.getInt("idCadena"));
        cadena.setNombreCadena(result.getString("nombreCadena"));
        cadena.setImagenCadena(result.getString("imagenCadena"));
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
