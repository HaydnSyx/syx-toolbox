package cn.syx.toolbox.strategy.lb;

import cn.syx.toolbox.strategy.lb.impl.RoundRibbonLoadBalancer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoadBalancerToolTest {

    @Test
    public void test() {
        LoadBalancerTool manager = LoadBalancerTool.getInstance();
        LoadBalancer<String> loadBalancer = manager.get(RoundRibbonLoadBalancer.class);

        List<String> instances = List.of("1", "2", "3");
        String data = loadBalancer.choose(instances);
        Assertions.assertEquals("1", data);
        data = loadBalancer.choose(instances);
        Assertions.assertEquals("2", data);
        data = loadBalancer.choose(instances);
        Assertions.assertEquals("3", data);
        data = loadBalancer.choose(instances);
        Assertions.assertEquals("1", data);
    }
}
