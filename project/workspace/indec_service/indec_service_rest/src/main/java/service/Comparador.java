package service;
import db.beans.Cadena;
import db.beans.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Comparador {

    public Comparador(){}

    private static final Logger logger = LoggerFactory.getLogger(Comparador.class);

    public static List<Cadena> compararPrecios (final List<Cadena> cadenas) throws IllegalArgumentException {

        List<Cadena> cadenasDisponibles = new LinkedList<>();

        List<Cadena> cadenasNoDisponibles = new LinkedList<>();

        if (cadenas == null)
            throw new IllegalArgumentException("El parametro cadenas is null");

        if(!cadenasDisponibles.isEmpty()){
            List<Cadena> cadenasDisponiblesMarcadas =
                    marcarSucursales(
                            marcarProductosMasBajos(cadenasDisponibles)
                    );

            return Stream.concat(cadenasDisponiblesMarcadas.stream(), cadenasNoDisponibles.stream()).collect(toList());
        }
        else return cadenasNoDisponibles;

    }

    private static  List<Cadena> marcarProductosMasBajos(final List<Cadena> cadenas){

        final List<Cadena> cadenasConProductosMarcados = cadenas;

        final Map<String,Float> preciosMasBajos  = buscarPreciosMasBajos(cadenas);

        for (Cadena c : cadenasConProductosMarcados) {
            for (Sucursal s :  c.getSucursales()) {
                for (ProductoSucursal p : s.getProductos()) {

                    Float precioMasBajo = preciosMasBajos.get(p.getCodigoDeBarras());

                    if (p.getPrecio().equals(precioMasBajo)) {
                        p.setMejorPrecio(true);
                    } else {
                        p.setMejorPrecio(false);
                    }
                }
            }
        }
        return cadenasConProductosMarcados;
    }

    private static  List<Cadena>  marcarSucursales (final List<Cadena> cadenas) {

        final List<Cadena> cadenasConSucursalesMarcadas =  cadenas;

        List <Long> cantidades = new LinkedList<>();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                long cantidadDeProductosConPrecioMasBajo =
                        (s.getProductos().stream().filter(p -> p.isMejorPrecio()).count());

                s.setCantidadDeProductosConPrecioMasBajo(cantidadDeProductosConPrecioMasBajo);

                cantidades.add(cantidadDeProductosConPrecioMasBajo);
            }
        }

        final Long cantidad_max = cantidades.stream().max(naturalOrder()).get();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                if(cantidad_max.equals(s.getCantidadDeProductosConPrecioMasBajo()))
                     s.setMejorOpcion(true);
                else s.setMejorOpcion(false);

            }
        }

        return cadenasConSucursalesMarcadas;
    }

    private static  Map<String,Float> buscarPreciosMasBajos(final List<Cadena> cadenasDisponibles){


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

    public static List<Cadena> completarProductosFaltantes(final List<Cadena> cadenas, final List<Producto> productos) {

        List<Cadena> cadenasConProductosFaltantes = cadenas;

        for (Cadena c : cadenasConProductosFaltantes) {
            if(c.getDisponible()){
                for (Sucursal s : c.getSucursales()) {
                    List<ProductoSucursal> productosAusentes = new LinkedList<>();
                    for (Producto p : productos) {
                        boolean existe = exists(p.getCodigoDeBarras(),s.getProductos());
                        if(!existe){
                            ProductoSucursal newProduct = new ProductoSucursal();
                            newProduct.setDisponible(false);
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
        }

        return cadenasConProductosFaltantes;
    }

    private static boolean exists(final String codigoDeBarras,List<ProductoSucursal>productos){
        for (ProductoSucursal producto : productos) {
            if(producto.getCodigoDeBarras().equals(codigoDeBarras)){
                return true;
            }
        }
        return false;
    }
}
