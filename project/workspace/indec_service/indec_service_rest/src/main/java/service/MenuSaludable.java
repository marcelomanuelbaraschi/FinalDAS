package service;

import db.beans.*;
import db.Bean;
import db.DaoFactory;
import utilities.GSON;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;

public class MenuSaludable {


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

    public static List<Cadena> armarPlato
            (final  String codigoentidadfederal
            ,final  String localidad
            ,final  Integer idPlato) throws APIException {

        List<Producto> productosIngrediente = obtenerProductosPorPlato(idPlato);
        for (Producto producto : productosIngrediente) {
            System.out.println(producto.getCodigoDeBarras() + "-" + producto.getIdIngrediente());
        }

        String codigos =
                productosIngrediente.stream().map(p -> p.getCodigoDeBarras()).collect(Collectors.joining(","));

        List<Configuracion> configs = Cadenas.obtenerConfiguraciones();

        List<Cadena> cadenas = Cadenas.obtenerPrecios(codigoentidadfederal,localidad,codigos,configs);
//----------------------------------------------------------------------------------------------------------------------
        for (Cadena cadena : cadenas) {
            if (cadena.getDisponible()) {
                for (Sucursal sucursal : cadena.getSucursales()) {
                    for (ProductoSucursal producto : sucursal.getProductos()) {
                        int idIngrediente = 0;
                        for (Producto pi : productosIngrediente) {
                            if (pi.getCodigoDeBarras().equals(producto.getCodigoDeBarras()))
                                idIngrediente = pi.getIdIngrediente();
                        }
                        producto.setIdIngrediente(idIngrediente);
                    }
                }
            }
        }
//----------------------------------------------------------------------------------------------------------------------
        float precioTotal = 0;
        List <Long> cantidadesDeProductosPorSucursal = new LinkedList<>();
        for (Cadena cadena : cadenas) {
            if (cadena.getDisponible()) {

                for (Sucursal sucursal : cadena.getSucursales()) {
                    precioTotal = 0;
                    Map<Integer, List<ProductoSucursal>> productosPorIngrediente =
                            sucursal.getProductos().stream()
                                                   .collect(groupingBy(ProductoSucursal::getIdIngrediente));

                    final Map<Integer,ProductoSucursal> productoMasBaratoPorIngrediente = new HashMap<>();

                    productosPorIngrediente.forEach((ingrediente,productos)-> {
                                final ProductoSucursal pp =
                                        productos.stream().min(comparing(p -> p.getPrecio())).get();
                                        productoMasBaratoPorIngrediente.put(ingrediente,pp);
                            }
                    );

                    for (Map.Entry<Integer, ProductoSucursal> entry : productoMasBaratoPorIngrediente.entrySet()) {
                        precioTotal = precioTotal + entry.getValue().getPrecio();
                    }

                    ArrayList<ProductoSucursal> prods = new ArrayList<>(productoMasBaratoPorIngrediente.values());
                    sucursal.setProductos(prods);
                    sucursal.setCantidadDeProductosConPrecioMasBajo(sucursal.getProductos().stream().count());
                    cantidadesDeProductosPorSucursal.add(sucursal.getCantidadDeProductosConPrecioMasBajo());
                    sucursal.setTotal(precioTotal);
                }
            }
        }
//----------------------------------------------------------------------------------------------------------------------
        final long cantidad_max = cantidadesDeProductosPorSucursal.stream()
                                                                  .max(naturalOrder())
                                                                  .get();

        float menorPrecioTotal = Float.MAX_VALUE;
        for (Cadena c : cadenas) {
            for (Sucursal s : c.getSucursales()) {
                if(cantidad_max == s.getCantidadDeProductosConPrecioMasBajo()) {
                    s.setMejorOpcion(true);
                    if (s.getTotal() < menorPrecioTotal) {
                        menorPrecioTotal = s.getTotal();
                    }
                }
                else s.setMejorOpcion(false);

            }
        }

//----------------------------------------------------------------------------------------------------------------------
        for (Cadena c : cadenas) {
            for (Sucursal s : c.getSucursales()) {
                if(s.isMejorOpcion()&& s.getTotal() == menorPrecioTotal)
                    s.setMejorOpcion(true);
                else s.setMejorOpcion(false);

            }
        }
//----------------------------------------------------------------------------------------------------------------------
        return cadenas;
    }
}
