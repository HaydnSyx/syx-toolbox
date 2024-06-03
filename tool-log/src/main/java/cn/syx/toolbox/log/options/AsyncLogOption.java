package cn.syx.toolbox.log.options;

public class AsyncLogOption extends LogOption {

    /**
     * 丢弃日志阈值，默认不丢弃。如果要设置为队列满90%进行丢弃后续任务，则将下面配置为10
     */
    private int discardingThreshold = 0;
    /**
     * 异步队列大小
     */
    private int queueSize = 1024;
    /**
     * 是否从不阻塞（默认不阻塞）
     */
    private boolean neverBlock = true;
    /**
     * 是否获取调用者数据（默认不获取）
     * 启动此选项会导致性能下降，因为需要获取调用者的数据
     * 不启用在异步场景可能导致部分格式数据无法输出。如：%C %M %L会输出 ? ? ?
     */
    private boolean includeCallerData = false;

    private AsyncLogOption(Builder builder) {
        super(builder);
        this.discardingThreshold = builder.discardingThreshold;
        this.queueSize = builder.queueSize;
        this.neverBlock = builder.neverBlock;
        this.includeCallerData = builder.includeCallerData;
    }

    public static AsyncLogOption.Builder builder() {
        return new AsyncLogOption.Builder();
    }

    public int getDiscardingThreshold() {
        return discardingThreshold;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isNeverBlock() {
        return neverBlock;
    }

    public boolean isIncludeCallerData() {
        return includeCallerData;
    }

    public static class Builder extends LogOption.Builder<Builder> {
        private int discardingThreshold = 0;
        private int queueSize = 1024;
        private boolean neverBlock = true;
        private boolean includeCallerData = false;

        public Builder() {
        }

        // Setter methods for AsyncLogOption specific fields, returning the Builder for chaining

        public Builder discardingThreshold(int discardingThreshold) {
            this.discardingThreshold = discardingThreshold;
            return this;
        }

        public Builder queueSize(int queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public Builder neverBlock(boolean neverBlock) {
            this.neverBlock = neverBlock;
            return this;
        }

        public Builder includeCallerData(boolean includeCallerData) {
            this.includeCallerData = includeCallerData;
            return this;
        }

        // Overriding the build() method to return an instance of AsyncLogOption
        @Override
        public AsyncLogOption build() {
            return new AsyncLogOption(this);
        }
    }
}
