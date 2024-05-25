package cn.syx.toolbox.strategy.lb;

import java.util.List;

public interface LoadBalancer<T> {

    String type();

    T choose(List<T> instances);
}
