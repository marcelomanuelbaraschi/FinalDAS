package service.Actions;

import beans.CategoriaProducto;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObtenerCategorias  {
    public static List<CategoriaProducto> execute()throws APIException {
            try {
                List<Bean> categorias = DaoFactory.getDao("CategoriasProducto")
                        .select(null);
                return Arrays.asList(GSON.transform(categorias, CategoriaProducto[].class));
            } catch (SQLException ex) {
                throw new APIException(ex);
            }

    }
}
