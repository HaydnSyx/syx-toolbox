package cn.syx.toolbox.log;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AsyncAppenderBase;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.log.options.AsyncLogOption;
import cn.syx.toolbox.log.options.LogOption;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LogbackLoggerFactory {

    private static final ConcurrentHashMap<String, org.slf4j.Logger> LOGGER_MAP = new ConcurrentHashMap<>();

    public static org.slf4j.Logger getLogger(String name) {
        if (StringTool.isBlank(name)) {
            throw new RuntimeException("日志配置名字必须配置");
        }

        org.slf4j.Logger logger = LOGGER_MAP.get(name);
        if (Objects.isNull(logger)) {
            throw new RuntimeException(String.format("未找到对应的日志配置:name=%s", name));
        }

        return logger;
    }

    public static org.slf4j.Logger build(String name, LogOption option) {
        if (StringTool.isBlank(name)) {
            throw new RuntimeException("日志配置名字必须配置");
        }

        if (LOGGER_MAP.contains(name)) {
            return LOGGER_MAP.get(name);
        }

        if (Objects.isNull(option)) {
            throw new RuntimeException("日志配置必须配置");
        }
        if (StringTool.isBlank(option.getFilePath())) {
            throw new RuntimeException("日志文件路径必须配置");
        }
        if (StringTool.isBlank(option.getFileName())) {
            throw new RuntimeException("日志文件名称必须配置");
        }

        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            // 构建appender
            RollingFileAppender<ILoggingEvent> appender = new LoggerAppenderBuilder().createRollingFileAppender(context, name, option);
            // 生成logger
            Logger logger = context.getLogger(name);
            // 设置不向上级打印信息 不继承祖先appender
            logger.setAdditive(false);
            logger.addAppender(appender);

            LOGGER_MAP.put(name, logger);

            return logger;
        } catch (ScanException e) {
            throw new RuntimeException("生成Logger配置失败", e);
        }
    }

    public static org.slf4j.Logger build(String name, AsyncLogOption option) {
        if (StringTool.isBlank(name)) {
            throw new RuntimeException("日志配置名字必须配置");
        }

        if (LOGGER_MAP.contains(name)) {
            return LOGGER_MAP.get(name);
        }

        if (Objects.isNull(option)) {
            throw new RuntimeException("日志配置必须配置");
        }
        if (StringTool.isBlank(option.getFilePath())) {
            throw new RuntimeException("日志文件路径必须配置");
        }
        if (StringTool.isBlank(option.getFileName())) {
            throw new RuntimeException("日志文件名称必须配置");
        }

        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            // 构建appender
            AsyncAppender appender = new AsyncAppender();
            appender.setContext(context);
            appender.setDiscardingThreshold(option.getDiscardingThreshold());
            appender.setQueueSize(option.getQueueSize());
            appender.setNeverBlock(option.isNeverBlock());
            appender.setIncludeCallerData(option.isIncludeCallerData());
            appender.addAppender(new LoggerAppenderBuilder().createRollingFileAppender(context, name, option));
            appender.start();
            // 生成logger
            Logger logger = context.getLogger(name);
            // 设置不向上级打印信息 不继承祖先appender
            logger.setAdditive(false);
            logger.addAppender(appender);

            LOGGER_MAP.put(name, logger);

            return logger;
        } catch (ScanException e) {
            throw new RuntimeException("生成异步Logger配置失败", e);
        }
    }

    // 日志输出
    private static class LoggerAppenderBuilder {

        public RollingFileAppender<ILoggingEvent> createRollingFileAppender(LoggerContext context, String name, LogOption option) throws ScanException {

            RollingFileAppender<ILoggingEvent> appender = new RollingFileAppender<>();
            //这里设置级别过滤器
            appender.addFilter(createLevelFilter(option.getLevel()));
            // 设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            appender.setContext(context);
            //appender的name属性
            appender.setName(name);
            //设置文件名
            String fileName = option.getFilePath() + option.getFileName();
            appender.setFile(OptionHelper.substVars(fileName, context));
            // 追加
            appender.setAppend(true);
            // 并发安全
            appender.setPrudent(false);
            // 滚动策略
            appender.setRollingPolicy(createSizeAndTimeBasedRollingPolicy(fileName, context, appender, option));
            // 编码器
            appender.setEncoder(createEncoder(context, option));
            // 启动
            appender.start();
            return appender;
        }

        // 文件滚动策略
        private SizeAndTimeBasedRollingPolicy<ILoggingEvent> createSizeAndTimeBasedRollingPolicy(String fileName,
                                                                                  LoggerContext context,
                                                                                  FileAppender<ILoggingEvent> appender,
                                                                                  LogOption option) throws ScanException {
            //设置文件创建时间及大小的类
            SizeAndTimeBasedRollingPolicy<ILoggingEvent> policy = new SizeAndTimeBasedRollingPolicy<>();
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            policy.setContext(context);
            //文件名格式
            String fp = OptionHelper.substVars(fileName + option.getFileNamePattern(), context);
            //最大日志文件大小
            policy.setMaxFileSize(FileSize.valueOf(option.getMaxFileSize()));
            //设置文件名模式
            policy.setFileNamePattern(fp);
            //设置最大历史记录为50条
            policy.setMaxHistory(option.getMaxHistory());
            //总大小限制
            policy.setTotalSizeCap(FileSize.valueOf(option.getTotalSizeCap()));
            //设置父节点是appender
            policy.setParent(appender);
            // 启动
            policy.start();
            return policy;
        }

        // 编码器
        private PatternLayoutEncoder createEncoder(LoggerContext context, LogOption option) {
            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            encoder.setContext(context);
            encoder.setCharset(StandardCharsets.UTF_8);
            //设置格式
            encoder.setPattern(option.getPattern());
            // 启动
            encoder.start();
            return encoder;
        }

        // 级别过滤器
        private LevelFilter createLevelFilter(Level level) {
            LevelFilter levelFilter = new LevelFilter();
            levelFilter.setLevel(level);
            levelFilter.setOnMatch(FilterReply.ACCEPT);
            levelFilter.setOnMismatch(FilterReply.DENY);
            levelFilter.start();
            return levelFilter;
        }
    }
}
