package io.github.haydnsyx.toolbox.experiment;

import io.github.haydnsyx.toolbox.experiment.defer.IDefer;

import java.util.function.Supplier;

public class DeferTool {

    private DeferTool() {
        throw new UnsupportedOperationException();
    }

    public static <R> R of(IDefer defer, Supplier<R> supplier) {
        try {
            return supplier.get();
        } finally {
            defer.defer();
        }
    }
}