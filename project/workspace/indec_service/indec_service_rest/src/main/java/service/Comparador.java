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

    private  final Logger logger = LoggerFactory.getLogger(Comparador.class);

    public  List<Cadena> compararPrecios (final List<Cadena> cadenas, final List<Producto> productos) throws IllegalArgumentException {
        if (cadenas == null)
            throw new IllegalArgumentException("El parametro cadenas is null");

        List<Cadena> cadenasDisponibles = new LinkedList<>();

        List<Cadena> cadenasNoDisponibles = new LinkedList<>();


        for(Cadena cad : cadenas){
            if(cad.getDisponible())
                cadenasDisponibles.add(cad);
            if(!cad.getDisponible())
                cadenasNoDisponibles.add(cad);
        }

        if(!cadenasDisponibles.isEmpty()){
            List<Cadena> cadenasDisponiblesMarcadas =
                    marcarSucursales(
                            marcarProductosMasBajosYNoDisponibles(cadenasDisponibles,productos)
                    );

            return Stream.concat(cadenasDisponiblesMarcadas.stream(), cadenasNoDisponibles.stream()).collect(toList());
        }
        else return cadenasNoDisponibles;

    }

    private   List<Cadena> marcarProductosMasBajosYNoDisponibles(final List<Cadena> cadenas,final List<Producto> productos){

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


    private  boolean exists(final String codigoDeBarras,List<ProductoSucursal>productos){
        for (ProductoSucursal producto : productos) {
            if(producto.getCodigoDeBarras().equals(codigoDeBarras)){
                return true;
            }
        }
        return false;
    }
}
