package cn.syx.toolbox.strategy.lb;

import cn.syx.toolbox.strategy.lb.impl.RoundRibbonLoadBalancer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancerTool {

    private static final LoadBalancerTool INSTANCE = new LoadBalancerTool();

    private static final Map<Class<?>, LoadBalancer<?>> MAP = new ConcurrentHashMap<>();

    static {
        // 配置默认的负载均衡策略
        LoadBalancerTool.getInstance().registry(new RoundRibbonLoadBalancer<>());
    }

    private LoadBalancerTool() {
    }

    public static LoadBalancerTool getInstance() {
        return INSTANCE;
    }

    public void registry(LoadBalancer<?> loadBalancer) {
        MAP.putIfAbsent(loadBalancer.getClass(), loadBalancer);
    }

    public <T> LoadBalancer<T> get(Class<? extends LoadBalancer> clazz) {
        return (LoadBalancer<T>) MAP.get(clazz);
    }
}
