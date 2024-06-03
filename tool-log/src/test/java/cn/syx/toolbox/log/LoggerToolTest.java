package cn.syx.toolbox.log;

import cn.syx.toolbox.base.ThreadTool;
import cn.syx.toolbox.log.options.AsyncLogOption;
import cn.syx.toolbox.log.options.LogOption;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoggerToolTest {

//    @Test
    public void testSync() {
        LogOption option = LogOption.builder()
                .fileName("test")
                .filePath("/Users/syx/Documents/Logs/temp")
                .build();
        Logger logger = LoggerTool.buildLogger("test", option);
        for (int i = 0; i < 10; i++) {
            logger.info(
                    "【同步测试】当前时间戳={}, 请求ID={}",
                    System.currentTimeMillis(),
                    UUID.randomUUID().toString().replaceAll("-", "")
            );
        }
    }

//    @Test
    public void testAsync1() {
        AsyncLogOption option = AsyncLogOption.builder()
                .fileName("async-test")
                .filePath("/Users/syx/Documents/Logs/temp")
                .build();
        Logger logger = LoggerTool.buildLogger("asyncTest", option);
        logger.info(
                "【异步测试】当前时间戳={}, 请求ID={}",
                System.currentTimeMillis(),
                UUID.randomUUID().toString().replaceAll("-", "")
        );
        ThreadTool.sleep(500, TimeUnit.MILLISECONDS);
    }

//    @Test
    public void testAsync2() {
        AsyncLogOption option = AsyncLogOption.builder()
                .fileName("async-test")
                .filePath("/Users/syx/Documents/Logs/temp")
                .includeCallerData(true)
                .build();
        Logger logger = LoggerTool.buildLogger("asyncTest", option);
        for (int i = 0; i < 10; i++) {
            logger.info(
                    "【异步测试】当前时间戳={}, 请求ID={}",
                    System.currentTimeMillis(),
                    UUID.randomUUID().toString().replaceAll("-", "")
            );
        }
        ThreadTool.sleep(500, TimeUnit.MILLISECONDS);
    }
}
