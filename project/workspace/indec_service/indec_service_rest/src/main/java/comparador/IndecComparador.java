package comparador;

import beans.config_models.Configuracion;
import cadenasObjects.Producto;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import beans.common_models.Cadena;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndecComparador {
/*
    private static final Logger logger = LoggerFactory.getLogger(IndecComparador.class);
    private List<Cadena> cadenasDisponibles;
    private List<Cadena> cadenasNoDisponibles;
    private Map<String, Float> preciosMasBajos;
    private Map<Pair<Long,Long>,Long> preciosMasBajosPorSucursal;
    private Pair <Long,Long> sucursalesPorCadena;
    private List<Configuracion> configs;


    public IndecComparador(List<Configuracion> configs){
        this.cadenasDisponibles =  new LinkedList<Cadena>();
        this.cadenasNoDisponibles =  new LinkedList<Cadena>();
        this.preciosMasBajos = new HashMap <String, Float>();
        this.preciosMasBajosPorSucursal = new HashMap<Pair<Long,Long>,Long>();
        this.configs = configs;
    }

    //TODO IMPROVE PERFORMANCE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<Cadena> compararPrecios (final String codigoentidadfederal,
                                         final String localidad,
                                         final String codigos) {

        List<String> lcodigos = toList.apply(codigos);
        consultarCadenas(codigoentidadfederal, localidad, lcodigos);
        if(!cadenasDisponibles.isEmpty()){
            buscarPreciosMasBajos();
            marcarProductos();
            buscarMejorSucursal();
            marcarSucursales();
        }
        return Stream.concat(cadenasNoDisponibles.stream(), cadenasDisponibles.stream()).collect(Collectors.toList());
    }

    public List<Cadena> consultarSucursales (final String codigoentidadfederal,
                                             final String localidad) {
        consultarCadenas(codigoentidadfederal, localidad);
        return Stream.concat(cadenasNoDisponibles.stream(), cadenasDisponibles.stream()).collect(Collectors.toList());
    }


    private CadenaServiceContract buildClient(Configuracion config) throws ClientException {
        return ClientFactory.clientFor(config.getUrl(),Enum.valueOf(Tecnologia.class,config.getTecnologia()));
    }

    Function<String, List<String>> toList = commaSeparatedStr -> {
        String[] commaSeparatedArr = commaSeparatedStr.split("\\s*,\\s*");
        List<String> result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
        return result;
    };

    private void consultarCadenas(final String codigoentidadfederal,final String localidad,final List<String> lcodigos) {
        Cadena cadena;

        for (Configuracion config : configs) {

            cadena = new Cadena();
            try {

                final CadenaServiceContract client = buildClient(config);
                final List<Sucursal> sucursales = client.precios( codigoentidadfederal, localidad, lcodigos);

                for (Sucursal s : sucursales) {
                    s.setIdCadena(config.getIdCadena());
                }

                cadena.setId(config.getIdCadena());
                cadena.setNombre(config.getNombreCadena());
                cadena.setSucursales(sucursales);
                cadena.setDisponibilidad("Disponible");
                this.cadenasDisponibles.add(cadena);

            } catch ( Exception e) {
                cadena.setId(config.getId());
                cadena.setNombre(config.getNombreCadena());
                cadena.setDisponibilidad("No Disponible");
                this.cadenasNoDisponibles.add(cadena);
            }
        }
    }

    private void consultarCadenas (final String codigoentidadfederal,final String localidad) {
        Cadena cadena;
        for (Configuracion config : configs) {

            cadena = new Cadena();
            try {
                final CadenaServiceContract client = buildClient(config);

                final List<Sucursal> sucursales = client.sucursales(codigoentidadfederal, localidad);

                for (Sucursal s : sucursales) {
                    s.setIdCadena(config.getIdCadena());
                }

                cadena.setId(config.getIdCadena());
                cadena.setNombre(config.getNombreCadena());
                cadena.setSucursales(sucursales);
                cadena.setDisponibilidad("Disponible");
                this.cadenasDisponibles.add(cadena);

            } catch (ClientException e) {
                cadena.setId(config.getId());
                cadena.setNombre(config.getNombreCadena());
                cadena.setDisponibilidad("No Disponible");
                this.cadenasNoDisponibles.add(cadena);
            }
        }
    }

    private void buscarPreciosMasBajos(){

        Map<String, List<Producto>> productos = this.cadenasDisponibles.stream()
                .flatMap(cad -> cad.getSucursales().stream())
                .flatMap(suc -> suc.getProductos().stream())
                .collect(Collectors.groupingBy(Producto::getIdComercial));

        for (Map.Entry<String, List<Producto>> entry : productos.entrySet()) {
            String idComercial = entry.getKey();
            Float mejorPrecio =  entry.getValue()
                    .stream()
                    .min(Comparator.comparing(producto -> producto.getPrecio()))
                    .get()
                    .getPrecio();
            this.preciosMasBajos.put(idComercial,mejorPrecio);
        }
    }

    private void marcarProductos(){

        this.cadenasDisponibles.stream()
                    .flatMap(c -> c.getSucursales().stream())
                    .flatMap(s -> s.getProductos().stream())
                    .forEach(p -> {
                        Float precio = preciosMasBajos.get(p.getIdComercial());
                        if (p.getPrecio().equals(precio)) {
                            p.setMejorPrecio(true);
                        } else {
                            p.setMejorPrecio(false);
                        }
                    });
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
    */
}
