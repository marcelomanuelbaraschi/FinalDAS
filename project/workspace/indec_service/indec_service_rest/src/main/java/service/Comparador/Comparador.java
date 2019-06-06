package service.Comparador;
import db.beans.Cadena;
import db.beans.CriterioBusquedaProducto;
import db.beans.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Cadenas.Sucursal;
import service.CanastaBasica.CanastaBasica;
import service.Cadenas.ProductoSucursal;
import utilities.ListUtils;
import utilities.NumberUtils;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Comparador {

    private static final Logger logger =
            LoggerFactory.getLogger(Comparador.class);

    public  List<Cadena> compararPrecios (final List<Cadena> cadenas, final List<Producto> productosSeleccionados){

            List<Cadena> cadenasDisponibles = new LinkedList<>();
            List<Cadena> cadenasNoDisponibles = new LinkedList<>();

            //Separamos las cadenasDisponibles de las cadenasNoDisponibles
            for(Cadena cad : cadenas){
                if(cad.getDisponible())
                    cadenasDisponibles.add(cad);
                if(!cad.getDisponible())
                    cadenasNoDisponibles.add(cad);
            }

            //Si existen cadenasDisponibles
            if(!cadenasDisponibles.isEmpty()){
                //Primero marcamos los productos
                //Luego marcamos las cadenas.
                List<Cadena> cadenasDisponiblesMarcadas =
                        marcarSucursales(
                                marcarProductos(cadenasDisponibles,productosSeleccionados)
                        );

                  return  Stream.concat(cadenasDisponiblesMarcadas.stream(), cadenasNoDisponibles.stream())
                                .collect(toList());

            }

            return cadenasNoDisponibles;

    }

    private   List<Cadena> marcarProductos(final List<Cadena> cadenas,final List<Producto> productos){

        //Asignamos el parametro para no mutarlo.
        List<Cadena> cadenasConProductosMarcados = cadenas;

        //Buscamos los mejores precios por codigo de barras.
        final Map<String,Float> preciosMasBajos  = buscarPreciosMasBajos(cadenas);

        float precioTotal = 0;

        for (Cadena c : cadenasConProductosMarcados) {
            for (Sucursal s :  c.getSucursales()) {
                precioTotal = 0;
                for (ProductoSucursal p : s.getProductos()) {

                    precioTotal = precioTotal + p.getPrecio();

                    Float precioMasBajo = preciosMasBajos.get(p.getCodigoDeBarras());

                    if (p.getPrecio() == precioMasBajo) {
                        p.setMejorPrecio(true);
                    } else {
                        p.setMejorPrecio(false);
                    }
                }
                s.setTotal(NumberUtils.round(precioTotal,2)); //TODO: causando problemas..

                List<ProductoSucursal> productosAusentes = new LinkedList<>();
                for (Producto p : productos) {
                    boolean existe = exists(p.getCodigoDeBarras(),s.getProductos());
                    if(!existe){
                        ProductoSucursal newProduct = new ProductoSucursal();
                        newProduct.setMejorPrecio(false);
                        newProduct.setPrecio((float) 0);
                        newProduct.setCodigoDeBarras(p.getCodigoDeBarras());
                        newProduct.setNombre(p.getNombreProducto());
                        newProduct.setMarca(p.getNombreMarca());
                        productosAusentes.add(newProduct);
                    }
                }
                List<ProductoSucursal> allProducts =
                        Stream.concat(s.getProductos().stream(), productosAusentes.stream()).collect(toList());
                s.setProductos(allProducts);

            }
        }
        return cadenasConProductosMarcados;
    }

    private   List<Cadena>  marcarSucursales (final List<Cadena> cadenas) {

        List<Cadena> cadenasConSucursalesMarcadas =  cadenas;

        List <Long> cantidades = new LinkedList<>();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                long cantidadDeProductosConPrecioMasBajo = 0L;
                for(ProductoSucursal p : s.getProductos()){
                    if(p.isMejorPrecio())
                        cantidadDeProductosConPrecioMasBajo = cantidadDeProductosConPrecioMasBajo + 1L;
                }
                s.setCantidadDeProductosConPrecioMasBajo(cantidadDeProductosConPrecioMasBajo);

                cantidades.add(cantidadDeProductosConPrecioMasBajo);
            }
        }

        final long cantidad_max = cantidades.stream().max(naturalOrder()).get().longValue();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                if(cantidad_max == s.getCantidadDeProductosConPrecioMasBajo())
                    s.setMejorOpcion(true);
                else s.setMejorOpcion(false);

            }
        }

        return cadenasConSucursalesMarcadas;
    }

    private   Map<String,Float> buscarPreciosMasBajos(final List<Cadena> cadenasDisponibles){


        final Map<String, List<ProductoSucursal>> productosPorCodigoDeBarra =

                cadenasDisponibles.stream()
                        .flatMap(cad -> cad.getSucursales().stream())
                        .filter(suc -> !suc.getProductos().isEmpty())
                        .flatMap(suc -> suc.getProductos().stream())
                        .collect(groupingBy(ProductoSucursal::getCodigoDeBarras));


        final Map<String,Float> preciosMasBajosPorCodigoDeBarra = new HashMap<>();

        productosPorCodigoDeBarra.forEach((codigoDeBarras,productos)-> {
                    final Float precioMasBajo =
                            productos.stream().min(comparing(p -> p.getPrecio())).get().getPrecio();
                    preciosMasBajosPorCodigoDeBarra.put(codigoDeBarras,precioMasBajo);

                }
        );

        return preciosMasBajosPorCodigoDeBarra;

    }

    private   boolean exists(final String codigoDeBarras,List<ProductoSucursal>productos){
        for (ProductoSucursal producto : productos) {
            if(producto.getCodigoDeBarras().equals(codigoDeBarras)){
                return true;
            }
        }
        return false;
    }
    

}
