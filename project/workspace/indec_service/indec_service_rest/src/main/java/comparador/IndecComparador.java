package comparador;

import beans.CadenaServiceConfigBean;
import beans.ComparadorResponse;
import cadenasObjects.Producto;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import indecObjects.Cadena;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.IndecRepository;
import repository.exceptions.RepositoryException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndecComparador {

    private static final Logger logger = LoggerFactory.getLogger(IndecRepository.class);
    private static volatile IndecComparador comparadorInstance;

    private IndecComparador(){}

    public static IndecComparador getInstance(){
        if (comparadorInstance == null){
            comparadorInstance = new IndecComparador();
        }

        return comparadorInstance;
    }


    public List<Cadena> compararPrecios (final String codigoentidadfederal, final String localidad, final String codigos)
            throws RepositoryException {

        List<String> lcodigos = toList.apply(codigos);
        final List<Cadena> cadenas;

        try{
            cadenas = consultarCadenas(codigoentidadfederal, localidad, lcodigos);
        }
        catch(RepositoryException ex){
            logger.error("Error en el metodo compararPrecios:{}",ex.getMessage());
            throw new RepositoryException(ex.getMessage());
        }

        Map<String, Float> preciosMasBajos = buscarPreciosMasBajos(cadenas);
        List<Cadena> cadenasMarcadas = marcarProductosMasBaratos(cadenas,preciosMasBajos);


        Map<Pair<Long,Long>,Long> mapa = new HashMap<>();
        for (Cadena c : cadenasMarcadas){
            for(Sucursal s :c.getSucursales()){
                mapa.put(Pair.with(c.getId(),s.getIdSucursal()),s.getProductos().stream().filter(p -> p.getMejorPrecio() ).count());
            }
        }

        Pair <Long,Long> key = Collections.max(mapa.entrySet(), Map.Entry.comparingByValue()).getKey();

        for (Cadena c : cadenasMarcadas){
            for(Sucursal s :c.getSucursales()){

                if ( c.getId().equals(key.getValue0()) &&  s.getIdSucursal().equals(key.getValue1()) ){
                    s.setMejorOpcion(true);
                } else s.setMejorOpcion(false);
            }
        }

        return cadenasMarcadas;
    }


    private CadenaServiceContract buildClient(CadenaServiceConfigBean config) throws ClientException {
        return ClientFactory.getInstance()
                .clientFor(Enum.valueOf(Tecnologia.class,config.getTecnologia()),config.getUrl());
    }

    Function<String, List<String>> toList = commaSeparatedStr -> {
        String[] commaSeparatedArr = commaSeparatedStr.split("\\s*,\\s*");
        List<String> result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
        return result;
    };

    private List <Cadena> consultarCadenas(final String codigoentidadfederal,final String localidad,final List<String> lcodigos) throws RepositoryException {

        List<Cadena> cadenasDisponibles = new LinkedList<Cadena>();
        List<Cadena> cadenasNoDisponibles = new LinkedList<Cadena>();
        Cadena cadena;
        final List<CadenaServiceConfigBean> configs = IndecRepository.getInstance().configs();
        for (CadenaServiceConfigBean config : configs) {

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
                cadenasDisponibles.add(cadena);

            } catch (ClientException e) {
                cadena.setId(config.getId());
                cadena.setNombre(config.getNombreCadena());
                cadena.setDisponibilidad("No Disponible");
                cadenasNoDisponibles.add(cadena);
            }
        }
        return Stream.concat(cadenasNoDisponibles.stream(), cadenasDisponibles.stream()).collect(Collectors.toList());
    }

    private Map <String, Float> buscarPreciosMasBajos(List<Cadena> cadenas){

        Map<String, List<Producto>> productos = cadenas.stream()
                .filter(cad->cad.getDisponibilidad().equals("Disponible"))
                .flatMap(cad -> cad.getSucursales().stream())
                .flatMap(suc -> suc.getProductos().stream())
                .collect(Collectors.groupingBy(Producto::getIdComercial));

        Map <String, Float> preciosMasBajos = new HashMap<String, Float>();
        for (Map.Entry<String, List<Producto>> entry : productos.entrySet()) {
            String idComercial = entry.getKey();
            Float mejorPrecio =  entry.getValue()
                    .stream()
                    .min(Comparator.comparing(producto -> producto.getPrecio()))
                    .get()
                    .getPrecio();
            preciosMasBajos.put(idComercial,mejorPrecio);
        }
        return preciosMasBajos;

    }

    //TODO BUSCAR MANERA DE NO MODIFICAR EL PARAMETRO CADENAS
    private List<Cadena> marcarProductosMasBaratos (List<Cadena> cadenas , final  Map <String, Float> preciosMasBajos){

        cadenas.stream()
                .filter(c -> c.getDisponibilidad().equals("Disponible"))
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
        return cadenas;
    }

}
