package io.github.haydnsyx.toolbox.strategy.lb.impl;

import io.github.haydnsyx.toolbox.strategy.lb.LoadBalancer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRibbonLoadBalancer<T> implements LoadBalancer<T> {

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public T choose(List<T> instances) {
        if (Objects.isNull(instances) || instances.isEmpty()) {
            return null;
        }

        if (instances.size() == 1) {
            return instances.get(0);
        }

        return instances.get((index.getAndIncrement() & Integer.MAX_VALUE) % instances.size());
    }
}
