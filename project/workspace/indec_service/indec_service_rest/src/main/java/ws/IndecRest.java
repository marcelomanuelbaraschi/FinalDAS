package ws;


import beans.*;
import cadenasObjects.PreciosSucursal;
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
import utilities.JsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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

        System.out.println("salio");

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


                        final List<PreciosSucursal> preciosSucursales =
                                client.precios("INDEC",codigoentidadfederal,localidad, Helper.fromCsvToList(codigos));

                        cadena = new Cadena();
                        cadena.setId(conf.getId());
                        cadena.setNombre(conf.getNombreCadena());
                        cadena.setSucursales(preciosSucursales);
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
            System.out.println("salio del  for");

        }

        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }

        //TODO USE UTILS DONDE SEA POJIBLE
        System.out.println("llego return");
        return JsonUtils.toJsonString(cadenas);
    }


}

