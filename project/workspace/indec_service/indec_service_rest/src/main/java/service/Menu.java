package service;

import beans.CadenaBean;
import beans.Plato;
import beans.Producto;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Menu {


    public static List<beans.Menu> obtenerMenuSemanal() throws APIException
    {
        try {
            List<Bean> menuSemanal = DaoFactory.getDao("MenuSemanal")
                    .select(null);
            return Arrays.asList(GSON.transform(menuSemanal, beans.Menu[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }


    public static List<Producto> obtenerProductosPorPlato(Integer idPlato) throws APIException{

        Plato plato = new Plato();
        plato.setIdPlato(idPlato);
        try {
            List<Bean> productos = DaoFactory.getDao("ProductosPorPlato")
                    .select(plato);
            return Arrays.asList(GSON.transform(productos, Producto[].class));
        } catch (SQLException ex) {
            throw new APIException(ex);
        }

    }

    public List<CadenaBean> armarPlato
            (final  String codigoentidadfederal
                    ,final  String localidad
                    ,final  Integer idPlato) throws APIException {

        /*List<Producto> listaDeProductos = ObtenerProductosPorPlato.execute(idPlato);
        String codigos = listaDeProductos.stream()
                .map(p -> p.getCodigoDeBarras())
                .collect(Collectors.joining(","));
        List<CadenaBean> cadenas = ObtenerPreciosPorCadena.execute(codigoentidadfederal, localidad, codigos);
        */return null;
    }
}
