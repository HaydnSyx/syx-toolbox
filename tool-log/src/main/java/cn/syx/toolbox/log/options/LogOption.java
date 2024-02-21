package cn.syx.toolbox.log.options;

import ch.qos.logback.classic.Level;

public class LogOption {

    public static final LogOption DEFAULT_OPTION = LogOption.builder().build();

    /**
     * 日志等级
     */
    private Level level = Level.INFO;
    /**
     * 日志文件路径
     */
    private String filePath;
    /**
     * 日志文件名称
     */
    private String fileName;
    /**
     * 文件滚动格式
     */
    private String fileNamePattern = ".%d{yyyy-MM-dd}.%i";
    /**
     * 文件最大容量
     */
    private String maxFileSize = "1G";
    /**
     * 总容量
     */
    private String totalSizeCap = "5G";
    /**
     * 最大量
     */
    private int maxHistory = 5;
    /**
     * 日志格式
     */
    private String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} %msg%n";

    protected LogOption(Builder builder) {
        this.level = builder.level;
        this.filePath = builder.filePath;
        this.fileName = builder.fileName;
        this.fileNamePattern = builder.fileNamePattern;
        this.maxFileSize = builder.maxFileSize;
        this.totalSizeCap = builder.totalSizeCap;
        this.maxHistory = builder.maxHistory;
        this.pattern = builder.pattern;
    }

    public static LogOption.Builder builder() {
        return new LogOption.Builder();
    }

    public Level getLevel() {
        return level;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public String getTotalSizeCap() {
        return totalSizeCap;
    }

    public int getMaxHistory() {
        return maxHistory;
    }

    public String getPattern() {
        return pattern;
    }

    public static class Builder {
        private Level level = Level.INFO;
        private String filePath;
        private String fileName;
        private String fileNamePattern = ".%d{yyyy-MM-dd}.%i";
        private String maxFileSize = "1G";
        private String totalSizeCap = "5G";
        private int maxHistory = 5;
        private String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} %msg%n";

        public Builder() {
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Builder filePath(String filePath) {
            if (filePath.endsWith("/")) {
                filePath = filePath.substring(0, filePath.length() - 1);
            }
            this.filePath = filePath;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileNamePattern(String fileNamePattern) {
            this.fileNamePattern = fileNamePattern;
            return this;
        }

        public Builder maxFileSize(String maxFileSize) {
            this.maxFileSize = maxFileSize;
            return this;
        }

        public Builder totalSizeCap(String totalSizeCap) {
            this.totalSizeCap = totalSizeCap;
            return this;
        }

        public Builder maxHistory(int maxHistory) {
            this.maxHistory = maxHistory;
            return this;
        }

        public Builder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public LogOption build() {
            return new LogOption(this);
        }
    }
}
