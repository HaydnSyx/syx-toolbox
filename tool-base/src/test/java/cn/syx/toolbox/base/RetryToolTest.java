package cn.syx.toolbox.base;

import cn.syx.toolbox.base.exception.RetryException;
import cn.syx.toolbox.base.retry.RetryPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * 重试相关工具测试类
 *
 * @author syx
 */
public class RetryToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(RetryTool.class));
    }

    @Test
    public void testRetry() {
        // 默认策略, 无降级
        RetryUnitTest retryUnitTest = new RetryUnitTest();
        String result = RetryTool.execute(retryUnitTest::helloWordWithNormal);
        Assertions.assertEquals("Hello, World! num=1", result);

        // 默认策略, 无降级
        retryUnitTest = new RetryUnitTest();
        result = RetryTool.execute(retryUnitTest::helloWordWithRetry);
        Assertions.assertEquals("Hello, World! num=2", result);

        // 默认策略, 无降级
        Assertions.assertThrows(RuntimeException.class, () -> {
            RetryUnitTest unitTest = new RetryUnitTest();
            RetryTool.execute(unitTest::helloWordWithError);
        });

        // 含降级策略
        RetryPolicy policy = RetryPolicy.builder()
                .degradeOnThrow(new RuntimeException())
                .build();
        retryUnitTest = new RetryUnitTest();
        result = RetryTool.execute(policy, retryUnitTest::helloWordWithError, retryUnitTest::helloWordWithDegrade);
        Assertions.assertEquals("Hello, Degrade! num=1", result);
    }

    public class RetryUnitTest {

        private int retryNum = 1;

        public String helloWordWithNormal() {
            return "Hello, World! num=" + retryNum;
        }

        public String helloWordWithRetry() {
            if (retryNum == 2) {
                return "Hello, World! num=" + retryNum;
            }
            retryNum++;
            throw new RetryException();
        }

        public String helloWordWithError() {
            throw new RuntimeException();
        }

        public String helloWordWithDegrade() {
            return "Hello, Degrade! num=" + retryNum;
        }
    }
}
