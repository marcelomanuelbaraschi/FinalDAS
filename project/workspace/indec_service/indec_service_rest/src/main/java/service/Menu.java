package service;

import db.beans.Cadena;
import db.beans.Plato;
import db.beans.Producto;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Menu {


    public static List<Menu> obtenerMenuSemanal() throws APIException
    {
        try {
            List<Bean> menuSemanal = DaoFactory.getDao("MenuSemanal")
                    .select(null);
            return Arrays.asList(GSON.transform(menuSemanal, Menu[].class));
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

    public List<Cadena> armarPlato
            (final  String codigoentidadfederal
                    ,final  String localidad
                    ,final  Integer idPlato) throws APIException {

        /*List<ProductoSucursal> listaDeProductos = ObtenerProductosPorPlato.execute(idPlato);
        String codigos = listaDeProductos.stream()
                .map(p -> p.getCodigoDeBarras())
                .collect(Collectors.joining(","));
        List<Cadena> cadenas = ObtenerPreciosPorCadena.execute(codigoentidadfederal, localidad, codigos);
        */return null;
    }
}
