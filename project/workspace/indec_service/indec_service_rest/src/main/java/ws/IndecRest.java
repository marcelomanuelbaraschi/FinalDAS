package ws;


import beans.*;
import cadenasObjects.Producto;
import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import db.Bean;
import db.Dao;
import db.DaoFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import indecObjects.Cadena;
import indecObjects.Helper;
import org.javatuples.Pair;
import utilities.JsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/app")
public class IndecRest {

    //protected static final Logger log = LoggerFactory.getLogger(IndecRest.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health(@QueryParam("identificador") final String identificador) {
        System.out.println("Rest health identificador ->" + identificador);
        return "OK";
    }

    @GET
    @Path("/traversalhealth")
    @Produces(MediaType.APPLICATION_JSON)
    public String traversalhealth(@QueryParam("identificador") final String identificador) {
        System.out.println("Rest Traversalhealth identificador ->" + identificador);

        CadenaServiceConfigBean configrest = new CadenaServiceConfigBean();
        configrest.setNombreCadena("Libertad");
        configrest.setTecnologia("REST");
        configrest.setUrl("http://localhost:8001/cadena_rest_one/cadenaRestOne");

        CadenaServiceConfigBean configaxis = new CadenaServiceConfigBean();

        configaxis.setNombreCadena("Walmart");
        configaxis.setTecnologia("SOAP");
        configaxis.setUrl("http://localhost:8000/cadena_axis_one/services/CadenaAxisOne?wsdl");

        CadenaServiceConfigBean configcxf = new CadenaServiceConfigBean();
        configcxf.setNombreCadena("Jumbo");
        configcxf.setTecnologia("SOAP");
        configcxf.setUrl("http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl");

        final List<CadenaServiceConfigBean> lconfis = new LinkedList<>();
        lconfis.add(configrest);
        lconfis.add(configaxis);
        lconfis.add(configcxf);

        String okAccum = "";
        for (CadenaServiceConfigBean conf:lconfis){
            try {
                final CadenaServiceContract client =
                        ClientFactory.getInstance()
                                .clientFor(Enum.valueOf(Tecnologia.class,conf.getTecnologia())
                                        ,conf.getUrl());

                okAccum = okAccum + conf.getTecnologia() +  client.health("INDEC") + ",";


            } catch (Exception e) {
                System.out.println("error.."+ e.getMessage());
                okAccum = okAccum + conf.getTecnologia() +  "NOK" + ",";
            }
        }
        return okAccum;

    }


    @GET
    @Path("/categorias")
    @Produces(MediaType.APPLICATION_JSON)
    public String categorias (@QueryParam("identificador") final String identificador) {

        System.out.println("Rest categorias identificador ->" + identificador);
        try {
            CategoriaProductoBean categoria = new CategoriaProductoBean();
            Dao dao = DaoFactory.getDao("CategoriasProducto", "");
            List<Bean> categorias = dao.select(categoria);
            return gson.toJson(categorias);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @GET
    @Path("/productos")
    @Produces(MediaType.APPLICATION_JSON)
    public String productos (@QueryParam("identificador") final String identificador,
                             @QueryParam("idcategoria") final Long idCategoria) {

        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(idCategoria);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            return gson.toJson(productos);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @GET
    @Path("/provincias")
    @Produces(MediaType.APPLICATION_JSON)
    public String provincias (@QueryParam("identificador") final String identificador) {
        try {
            ProvinciaBean provincia = new ProvinciaBean();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            return (gson.toJson(provincias));
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @GET
    @Path("/localidades")
    @Produces(MediaType.APPLICATION_JSON)
    public String localidades (@QueryParam("identificador") final String identificador,
                               @QueryParam("idprovincia") final Long idProvincia) {
        try {
            LocalidadBean localidad = new LocalidadBean();
            localidad.setIdProv(idProvincia);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            return (gson.toJson(localidades));
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            return "must be a proper error";
        }
    }

    @POST
    @Path("/precios")
    @Produces(MediaType.APPLICATION_JSON)
    public String precios (@QueryParam("identificador") final String identificador
                          ,@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                          ,@QueryParam("localidad") final String localidad
                          ,@QueryParam("codigos") final String codigos) {

        List <Cadena> cadenas = new LinkedList<Cadena>();

        try {
            CadenaServiceConfigBean config = new CadenaServiceConfigBean();
            Dao dao = DaoFactory.getDao("CadenasServicesConfigs", "");
            List<Bean> configs = dao.select(config);
            final  CadenaServiceConfigBean []  arrconfigs  = JsonUtils.toObject(gson.toJson(configs) ,CadenaServiceConfigBean[].class);
            final List<CadenaServiceConfigBean> lconfis = Stream.of(arrconfigs).collect(Collectors.toList());
            Cadena cadena;
            for (CadenaServiceConfigBean conf:lconfis){
                    try {
                        final CadenaServiceContract client =
                                    ClientFactory.getInstance()
                                             .clientFor(Enum.valueOf(Tecnologia.class,conf.getTecnologia())
                                                                      ,conf.getUrl());


                        final List<Sucursal> sucursales =
                                client.precios("INDEC",codigoentidadfederal,localidad, Helper.fromCsvToList(codigos));

                        cadena = new Cadena();
                        cadena.setId(conf.getId());
                        cadena.setNombre(conf.getNombreCadena());
                        cadena.setSucursales(sucursales);
                        cadenas.add(cadena);
                        cadena.setDisponibilidad("Disponible");

                    } catch (Exception e) {
                        cadena = new Cadena();
                        cadena.setId(conf.getId());
                        cadena.setNombre(conf.getNombreCadena());
                        cadenas.add(cadena);
                        cadena.setDisponibilidad("No Disponible");
                    }
            }
        }

        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }

        //TODO USE UTILS DONDE SEA POJIBLE
        //TODO NEED BETTER WAY

        List <Producto >tempProductos = new LinkedList<>();
        for (Cadena c : cadenas){
            for(Sucursal s :c.getSucursales()){
                for (Producto p: s.getProductos()){
                    tempProductos.add(p);
                }
            }
        }

        Map<String, List<Producto>> m = tempProductos.stream().collect(Collectors.groupingBy(producto -> producto.getCodigoProducto()));

        Map <String, Float> cod_min = new HashMap<String, Float>();
        for (Map.Entry<String, List<Producto>> entry : m.entrySet()) {
            Producto a =  entry.getValue().stream().min( Comparator.comparing(producto -> producto.getPrecio()) ).get();
            cod_min.put(entry.getKey(),a.getPrecio());
        }

        for (Cadena c : cadenas){
            for(Sucursal s :c.getSucursales()){
                for (Producto p: s.getProductos()){
                    Float precio =  cod_min.get(p.getCodigoProducto());
                    if ( p.getPrecio().equals(precio)) {
                        p.setMejorPrecio(true);
                    } else {
                        p.setMejorPrecio(false);
                    }
                }
            }
        }

        Map<Pair <Long,Long>,Long> mapa = new HashMap<>();
        for (Cadena c : cadenas){
            for(Sucursal s :c.getSucursales()){
                  mapa.put(Pair.with(c.getId(),s.getIdSucursal()),s.getProductos().stream().filter(p -> p.getMejorPrecio() ).count());
                }
        }

         mapa.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));

        Pair <Long,Long> key = Collections.max(mapa.entrySet(), Map.Entry.comparingByValue()).getKey();

        for (Cadena c : cadenas){
            for(Sucursal s :c.getSucursales()){
                if ( c.getId().equals(key.getValue0()) &&  s.getIdSucursal().equals(key.getValue1()) ){
                    s.setMejorOpcion(true);
                } else s.setMejorOpcion(false);
            }
        }

        return JsonUtils.toJsonString(cadenas);
    }


    @POST
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public String info (@QueryParam("identificador") final String identificador
                        ,@QueryParam("idsucursal") final Long idsucursal
                        ,@QueryParam("idcadena") final Long idcadena) {
        return null;
    }
}

