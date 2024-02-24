package cn.syx.toolbox.log;

import cn.syx.toolbox.log.options.LogOption;
import org.slf4j.Logger;

import java.util.Objects;

public class LoggerTool {

    public static boolean exist(String name) {
        return Objects.nonNull(LogbackLoggerFactory.getLogger(name));
    }

    public static Logger getLogger(String name) {
        return LogbackLoggerFactory.getLogger(name);
    }

    public static Logger getOrBuildSyncLogger(String name) {
        return LogbackLoggerFactory.getOrBuildLogger(name, false);
    }

    public static Logger getOrBuildASyncLogger(String name) {
        return LogbackLoggerFactory.getOrBuildLogger(name, true);
    }

    public static Logger buildLogger(String name, LogOption option) {
        return LogbackLoggerFactory.getOrBuildLogger(name, option);
    }
}
