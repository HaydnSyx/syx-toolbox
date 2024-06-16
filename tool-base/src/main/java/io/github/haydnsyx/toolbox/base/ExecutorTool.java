package io.github.haydnsyx.toolbox.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorTool {

    private ExecutorTool() {
        throw new UnsupportedOperationException();
    }

    public static void gracefulShutdown(ExecutorService executor) {
        gracefulShutdown(executor, 1000, TimeUnit.MILLISECONDS);
    }

    public static void gracefulShutdown(ExecutorService executor, long time, TimeUnit unit) {
        executor.shutdown();
        try {
            boolean re = executor.awaitTermination(time, unit);
            if (!executor.isTerminated() || !re) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            ConsoleTool.println(e.getMessage());
        }
    }
}
