package cn.syx.toolbox.strategy.lb;

import java.util.List;

public interface LoadBalancer<T> {

    T choose(List<T> instances);
}
