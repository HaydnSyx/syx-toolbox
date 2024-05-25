package cn.syx.toolbox.experiment;

import cn.syx.toolbox.base.ClassTool;
import cn.syx.toolbox.base.ThreadTool;
import cn.syx.toolbox.experiment.defer.CostTimeDefer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.concurrent.TimeUnit;

/**
 * Class相关工具
 *
 * @author syx
 */
public class DeferToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(DeferTool.class));
    }

    @Test
    public void testDefer() {
        DeferUnitTest unitTest = new DeferUnitTest();
        DeferTool.of(CostTimeDefer.create(), () -> {
            unitTest.hello();
            return null;
        });
    }

    public class DeferUnitTest {

        public void hello() {
            ThreadTool.sleep(100, TimeUnit.MILLISECONDS);
        }
    }
}
