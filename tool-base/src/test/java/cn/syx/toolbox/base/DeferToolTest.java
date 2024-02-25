package cn.syx.toolbox.base;

import cn.syx.toolbox.base.defer.CostTimeDefer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class DeferToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(DeferTool.class));
    }

    @Test
    public void testOf() {
        Supplier<Integer> supplier = () -> {
            try {
                return testMethod(1, 2, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        };

        Assertions.assertEquals(3, DeferTool.of(CostTimeDefer.create(), supplier));
        Assertions.assertEquals(3, DeferTool.of(CostTimeDefer.create(TimeUnit.NANOSECONDS), supplier));
        Assertions.assertEquals(3, DeferTool.of(CostTimeDefer.create(TimeUnit.MICROSECONDS), supplier));
        Assertions.assertEquals(3, DeferTool.of(CostTimeDefer.create(TimeUnit.SECONDS), supplier));
    }

    public static int testMethod(int a, int b, long time) throws InterruptedException {
        Thread.sleep(time);
        return a + b;
    }
}