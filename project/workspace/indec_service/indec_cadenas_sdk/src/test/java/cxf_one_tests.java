import cadenasObjects.Sucursal;
import clients.Tecnologia;
import clients.exceptions.ClientException;
import clients.factory.ClientFactory;
import contract.CadenaServiceContract;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class cxf_one_tests {

/*
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(8);

    private static <T> CompletableFuture<T> within
            (long duration, TimeUnit unit, CompletableFuture<T> future)
    {
        final CompletableFuture<T> timeout = failAfter(duration, unit);
        return future.applyToEither(timeout, Function.identity());
    }

    private static <T> CompletableFuture<T> failAfter
            (long duration, TimeUnit unit)
    {
        final CompletableFuture<T> future = new CompletableFuture<>();
        scheduler.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return future.completeExceptionally(ex);
        }, duration, unit);
        return future;
    }

    final String wsdlUrl = "http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl";

    @Test
    public void health_should_success() {

         final CadenaServiceContract client = ClientFactory.clientFor(wsdlUrl,Tecnologia.SOAP,1L);

         CompletableFuture<Void> future =
         within(1, SECONDS,
                supplyAsync(() -> client.health())
            ).thenAccept(
                    (confirmation) -> {assertEquals(confirmation.toLowerCase(), "ok");}
            ).exceptionally(
                    (exception) -> {fail(exception.getMessage());return null;}
            );
    }

   @Test
    public void call_to_sucursales_endpoint_should_success() {

        final CadenaServiceContract client = ClientFactory.clientFor(wsdlUrl,Tecnologia.SOAP,1L);
        CompletableFuture<Void> future =
               within(1, SECONDS,
                       supplyAsync(() -> client.sucursales("AR-X","Capital"))
               ).thenAccept(
                       (sucursales) -> { assertNotNull(sucursales); }
               ).exceptionally(
                       (exception) -> {fail(exception.getMessage());return null;}
               );
    }

    /*@Test
    public void call_to_precios_endpoint_should_success() {
        try {
            final CadenaServiceContract client = ClientFactory.clientFor(wsdlUrl,Tecnologia.SOAP);

            List <String> codigos = new LinkedList<>();
            codigos.add("7791708001231");
            codigos.add("7791708001378");

            final List<Sucursal> preciosSucursales =
                    client.precios("AR-X","Capital", codigos);

            assertTrue(true);
        } catch (ClientException e) {
            fail(e.getMessage());
        }
    }*/

}
