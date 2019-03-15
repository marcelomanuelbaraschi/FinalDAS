package daos;

import bean.MarcaBean;
import db.Bean;
import db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSMarcasDao extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		String marca = result.getString(1);
		MarcaBean bean = new MarcaBean();
		bean.setMarca(marca);
		return bean;
	}

	@Override
	public void insert(Bean form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Bean form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bean form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		MarcaBean    marca_param = (MarcaBean) bean;
		List<Bean>   marcas;
		this.connect();
		this.setProcedure("dbo.get_marcas(?)");
		if(marca_param.getMarca() == null) {
			this.setNull(1, Types.VARCHAR);
		}
		else {
			this.setParameter(1, marca_param.getMarca());
		}
		marcas = this.executeQuery();
		this.disconnect();
		return marcas;


	}

	@Override
	public boolean valid(Bean form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
