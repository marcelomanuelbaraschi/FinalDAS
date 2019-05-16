package service;
import beans.CadenaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sdkObjects.Sucursal;
import sdkObjects.Producto;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Comparador {

    private static final Logger logger = LoggerFactory.getLogger(Comparador.class);

    public List<CadenaBean> compararPrecios (final List<CadenaBean> cadenas) throws IllegalArgumentException {


        if (cadenas==null) throw new IllegalArgumentException("El parametro cadenas is null");

        List<CadenaBean> cadenasDisponibles = new LinkedList<>();
        List<CadenaBean> cadenasNoDisponibles = new LinkedList<>();

        for(CadenaBean cad : cadenas){
            if(cad.getSucursales()!= null)
                cadenasDisponibles.add(cad);
            if(cad.getSucursales()== null)
                cadenasNoDisponibles.add(cad);
        }


        if(!cadenasDisponibles.isEmpty()){
            List<CadenaBean> cadenasDisponiblesMarcadas =
                    marcarSucursales(
                            marcarProductosMasBajos(cadenasDisponibles)
                    );

            return Stream.concat(cadenasDisponiblesMarcadas.stream(), cadenasNoDisponibles.stream()).collect(toList());
        }
        else return cadenasNoDisponibles;

    }

    private   List<CadenaBean> marcarProductosMasBajos(final List<CadenaBean> cadenas){

        final List<CadenaBean> cadenasConProductosMarcados = cadenas;

        final Map<String,Float> preciosMasBajos  = buscarPreciosMasBajos(cadenas);

        for (CadenaBean c : cadenasConProductosMarcados) {
            for (Sucursal s :  c.getSucursales()) {
                for (Producto p : s.getProductos()) {

                    Float precioMasBajo = preciosMasBajos.get(p.getCodigoDeBarras());

                    if (p.getPrecio().equals(precioMasBajo)) {
                        p.setMejorOpcion(true);
                    } else {
                        p.setMejorOpcion(false);
                    }
                }
            }
        }
        return cadenasConProductosMarcados;
    }

    private   List<CadenaBean>  marcarSucursales (final List<CadenaBean> cadenas) {

        final List<CadenaBean> cadenasConSucursalesMarcadas =  cadenas;

        List <Long> cantidades = new LinkedList<>();

        for (CadenaBean c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                s.setCantidadDeProductosConPrecioMasBajo((s.getProductos().stream().filter(p -> p.isMejorOpcion()).count()));
                cantidades.add(s.getCantidadDeProductosConPrecioMasBajo());
            }
        }

        final Long cantidad_max = cantidades.stream().max(naturalOrder()).get();

        for (CadenaBean c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                if(cantidad_max.equals(s.getCantidadDeProductosConPrecioMasBajo()))
                     s.setMejorOpcion(true);
                else s.setMejorOpcion(false);

            }
        }

        return cadenasConSucursalesMarcadas;
    }

    private   Map<String,Float> buscarPreciosMasBajos(final List<CadenaBean> cadenasDisponibles){
        final Map<String, List<Producto>> productosPorCodigoDeBarra =
                cadenasDisponibles.stream()
                        .flatMap(cad -> cad.getSucursales().stream())
                        .flatMap(suc -> suc.getProductos().stream())
                        .collect(groupingBy(Producto::getCodigoDeBarras));


        final Map<String,Float> preciosMasBajosPorCodigoDeBarra = new HashMap<>();

        productosPorCodigoDeBarra.forEach((codigoDeBarras,productos)-> {
                    final Float precioMasBajo = productos.stream().min(comparing(p -> p.getPrecio())).get().getPrecio();
                    preciosMasBajosPorCodigoDeBarra.put(codigoDeBarras,precioMasBajo);

                }
        );

        return preciosMasBajosPorCodigoDeBarra;

    }

}
