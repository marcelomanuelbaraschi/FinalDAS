package ws;
import service.IndecService;
import utilities.JsonMarshaller;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static javax.ws.rs.core.Response.status;


@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class IndecRest extends IndecService {

    private final ExecutorService executor = Executors.newWorkStealingPool(4);

    @GET
    @Path("/categorias")
    public void categorias (@Suspended final AsyncResponse asyncResponse) {

        supplyAsync(getCategorias,executor)
            .thenApply((categorias) ->
                asyncResponse.resume(JsonMarshaller.toJson(categorias))
            ).exceptionally(e -> {
                System.out.println(e.getMessage()); //Todo: need propper logging
                return asyncResponse.resume(status(INTERNAL_SERVER_ERROR).entity(e).build());
            });

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler((ar) -> ar.resume(status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
    }

    @GET
    @Path("/productos")
    public void productos (@Suspended final AsyncResponse asyncResponse
                          ,@QueryParam("idcategoria") final Long idCategoria) {

        supplyAsync(() -> getProductos.apply(idCategoria),executor)
                .thenApply((productos) -> asyncResponse.resume(JsonMarshaller.toJson(productos)))
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //Todo: need propper logging
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR).entity(e).build());
                });

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
    }

    @GET
    @Path("/provincias")
    public void provincias (@Suspended final AsyncResponse asyncResponse) {

        supplyAsync(getProvincias,executor)
                .thenApply((provincias) -> asyncResponse.resume(JsonMarshaller.toJson(provincias)))
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //Todo: need propper logging
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR).entity(e).build());
                });

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
    }

    @GET
    @Path("/localidades")
    public void localidades (@Suspended final AsyncResponse asyncResponse
                            ,@QueryParam("codigoentidadfederal") final String codigoEntidadFederal) {

        supplyAsync(()-> getLocalidades.apply(codigoEntidadFederal),executor)
                .thenApply((localidades) -> asyncResponse.resume(JsonMarshaller.toJson(localidades)))
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //Todo: need propper logging
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR).entity(e).build());
                });

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
    }


    @GET
    @Path("/cadenas")
    public void cadenas (@Suspended final AsyncResponse asyncResponse) {

        supplyAsync(getCadenas,executor)
                .thenApply((cadenas) -> asyncResponse.resume(JsonMarshaller.toJson(cadenas)))
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //Todo: need propper logging
                    return asyncResponse.resume(status(INTERNAL_SERVER_ERROR).entity(e).build());
                });

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                status(SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
    }
























/*
    @POST
    @Path("/comparador")
    public Response comparador (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad
                               ,@QueryParam("codigos") final String codigos) {

     //TODO validar parametros
     //TODO agregar como parametro el criterio con el cual funcionara el comparador.

        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceFunctions.getInstance().compararPrecios(codigoentidadfederal,localidad,codigos))
            ).build();
        }
        catch (IndecServiceException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @ManagedAsync
    public void asynccomparador (
             @QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos
            ,@Suspended final AsyncResponse asyncResponse) {

        CompletableFuture<List<Cadena>> precios =
                supplyAsync(()->asyncompararPrecios(codigoentidadfederal,localidad,codigos));

        precios.thenApply((result) -> asyncResponse.resume(result))
               .exceptionally(e -> asyncResponse.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build()));

        asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));


    }

    @GET
    @Path("/sucursales")
    public Response sucursales (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
                               ,@QueryParam("localidad") final String localidad) {

        try{
            return Response.status(Response.Status.OK)
                    .entity(JsonMarshaller.toJson(IndecServiceFunctions.getInstance().sucursales(codigoentidadfederal,localidad))
                    ).build();
        }
        catch (IndecServiceException ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/health")
    public String health() {
        logger.debug("Healthy");
        return "OK";
    }*/


}






