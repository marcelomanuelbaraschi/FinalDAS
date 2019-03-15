package daos;

import beans.CadenaBean;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCadenasDao extends DaoImpl {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Long id = result.getLong(1);
        String nombre = result.getString(2);
        String tecnologia = result.getString(3);
        CadenaBean bean = new CadenaBean();
        bean.setId(id);
        bean.setNombre(nombre);
        bean.setTecnologia(tecnologia);
        return bean;
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
        List<Bean>   cadenas;
        this.connect();
        this.setProcedure("dbo.get_cadenas");
        cadenas = this.executeQuery();
        this.disconnect();
        return cadenas;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
