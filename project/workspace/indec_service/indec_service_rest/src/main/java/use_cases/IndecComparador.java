package use_cases;
import beans.Cadena;
import cadenasObjects.Producto;
import cadenasObjects.Sucursal;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndecComparador {

    private static final Logger logger = LoggerFactory.getLogger(use_cases.IndecComparador.class);
    private List<Cadena> cadenasDisponibles;
    private List<Cadena> cadenasNoDisponibles;
    private Map<String, Float> preciosMasBajos;
    private Map<Pair<Long,Long>,Long> preciosMasBajosPorSucursal;
    private Pair <Long,Long> sucursalesPorCadena;


    public IndecComparador(List<Cadena> cadenas){
        if (cadenas==null) throw new IllegalArgumentException("El parametro cadenas is null");
        this.cadenasDisponibles =  cadenas.stream()
                                          .filter(c-> c.getDisponibilidad().equals("Disponible"))
                                          .collect(Collectors.toList());
        this.cadenasNoDisponibles =  cadenas.stream()
                                            .filter(c-> c.getDisponibilidad().equals("No Disponible"))
                                            .collect(Collectors.toList());

        this.preciosMasBajos = new HashMap<>();
        this.preciosMasBajosPorSucursal = new HashMap<>();
    }

    public List<Cadena> compararPrecios () {

        if(!cadenasDisponibles.isEmpty()){
            buscarPreciosMasBajos();
            marcarProductos();
            buscarMejorSucursal();
            marcarSucursales();
        }
        return Stream.concat(cadenasNoDisponibles.stream(), cadenasDisponibles.stream()).collect(Collectors.toList());
    }


    private void buscarPreciosMasBajos(){

        Map<String, List<Producto>> productos = this.cadenasDisponibles.stream()
                .flatMap(cad -> cad.getSucursales().stream())
                .flatMap(suc -> suc.getProductos().stream())
                .collect(Collectors.groupingBy(Producto::getCodigoDeBarras));

        for (Map.Entry<String, List<Producto>> entry : productos.entrySet()) {
            String codigoDeBarras = entry.getKey();
            Float mejorPrecio =  entry.getValue()
                    .stream()
                    .min(Comparator.comparing(producto -> producto.getPrecio()))
                    .get()
                    .getPrecio();
            this.preciosMasBajos.put(codigoDeBarras,mejorPrecio);
        }
    }

    private void marcarProductos(){

        for (Cadena c : this.cadenasDisponibles) {
            for (Sucursal s : c.getSucursales()) {
                for (Producto p : s.getProductos()) {
                    Float precio = preciosMasBajos.get(p.getCodigoDeBarras());
                    if (p.getPrecio().equals(precio)) {
                        p.setMejorPrecio(true);
                    } else {
                        p.setMejorPrecio(false);
                    }
                }
            }
        }
    }

    private void buscarMejorSucursal(){
        for (Cadena c : this.cadenasDisponibles){
            for(Sucursal s :c.getSucursales()){
                this.preciosMasBajosPorSucursal.put(Pair.with(c.getId(),s.getIdSucursal()),s.getProductos().stream().filter(p -> p.getMejorPrecio() ).count());
            }
        }
    }

    private void marcarSucursales() {
        sucursalesPorCadena = Collections.max(this.preciosMasBajosPorSucursal.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (Cadena c : this.cadenasDisponibles){
            for(Sucursal s :c.getSucursales()){
                if ( c.getId().equals(sucursalesPorCadena.getValue0()) &&
                    s.getIdSucursal().equals(sucursalesPorCadena.getValue1()) ){
                         s.setMejorOpcion(true);
                } else s.setMejorOpcion(false);
            }
        }
    }

}
