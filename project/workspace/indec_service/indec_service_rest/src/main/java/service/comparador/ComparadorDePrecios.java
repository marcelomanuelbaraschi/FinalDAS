package service.comparador;
import beans.Cadena;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sdkObjects.Sucursal;
import sdkObjects.Producto;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class ComparadorDePrecios {

    private static final Logger logger = LoggerFactory.getLogger(ComparadorDePrecios.class);

    public static List<Cadena> compararPrecios (final List<Cadena> cadenas) throws IllegalArgumentException {


        if (cadenas==null) throw new IllegalArgumentException("El parametro cadenas is null");



        List<Cadena> cadenasDisponibles = new LinkedList<>();
        List<Cadena> cadenasNoDisponibles = new LinkedList<>();

        for(Cadena cad : cadenas){
            if(cad.getDisponibilidad().equals("Disponible") && cad.getSucursales()!= null)
                cadenasDisponibles.add(cad);
            if(cad.getDisponibilidad().equals("No Disponible") && cad.getSucursales()== null)
                cadenasNoDisponibles.add(cad);
            else throw new IllegalArgumentException("ComparadorDePrecios : El parametro no tiene el formato correcto..");
        }


        if(cadenasDisponibles.isEmpty()){
            List<Cadena> cadenasDisponiblesMarcadas = marcarSucursales(marcarProductosMasBajos(cadenasDisponibles));

            return Stream.concat(cadenasDisponiblesMarcadas.stream(), cadenasDisponibles.stream()).collect(toList());
        }
        else return cadenasNoDisponibles;

    }

    private static  List<Cadena> marcarProductosMasBajos(final List<Cadena> cadenas){

        final List<Cadena> cadenasConProductosMarcados = cadenas;

        final Map<String,Float> preciosMasBajos  = buscarPreciosMasBajos(cadenas);

        for (Cadena c : cadenasConProductosMarcados) {
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

    private static  List<Cadena>  marcarSucursales (final List<Cadena> cadenas) {

        final List<Cadena> cadenasConSucursalesMarcadas =  cadenas;

        List <Long> cantidades = new LinkedList<>();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                s.setCantidadDeProductosConPrecioMasBajo((s.getProductos().stream().filter(p -> p.isMejorOpcion()).count()));
                cantidades.add(s.getCantidadDeProductosConPrecioMasBajo());
            }
        }

        final Long cantidad_max = cantidades.stream().max(naturalOrder()).get();

        for (Cadena c : cadenasConSucursalesMarcadas) {
            for (Sucursal s : c.getSucursales()) {
                if(cantidad_max.equals(s.getCantidadDeProductosConPrecioMasBajo()))
                     s.setMejorOpcion(true);
                else s.setMejorOpcion(true);

            }
        }

        return cadenasConSucursalesMarcadas;
    }

    private static  Map<String,Float> buscarPreciosMasBajos(final List<Cadena> cadenasDisponibles){
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
