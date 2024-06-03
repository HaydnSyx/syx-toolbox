package cn.syx.toolbox.log;

import cn.syx.toolbox.log.options.AsyncLogOption;
import cn.syx.toolbox.log.options.LogOption;
import org.slf4j.Logger;

import java.util.Objects;

public class LoggerTool {

    public static boolean exist(String name) {
        return Objects.nonNull(LogbackLoggerFactory.getLogger(name));
    }

    public static Logger buildLogger(String name, LogOption option) {
        return LogbackLoggerFactory.build(name, option);
    }

    public static Logger buildLogger(String name, AsyncLogOption option) {
        return LogbackLoggerFactory.build(name, option);
    }

    public static Logger getLogger(String name) {
        return LogbackLoggerFactory.getLogger(name);
    }
}
