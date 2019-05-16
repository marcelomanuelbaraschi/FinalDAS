package ws;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class FutureOps {
    public static <T> CompletableFuture<T>
        within(long duration, TimeUnit unit, ScheduledExecutorService executor, CompletableFuture<T> future)
    {
        final CompletableFuture<T> timeout = failAfter(duration, unit, executor);
        return future.applyToEither(timeout, Function.identity());
    }

    private static  <T> CompletableFuture<T>
        failAfter(long duration, TimeUnit unit, ScheduledExecutorService executor)
    {
        final CompletableFuture<T> future = new CompletableFuture<>();
        executor.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return future.completeExceptionally(ex);
        }, duration, unit);
        return future;
    }
}
