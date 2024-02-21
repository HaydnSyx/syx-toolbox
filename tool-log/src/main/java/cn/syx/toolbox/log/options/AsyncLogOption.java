package cn.syx.toolbox.log.options;

public class AsyncLogOption extends LogOption {

    public static final LogOption DEFAULT_ASYNC_OPTION = AsyncLogOption.builder().build();

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

    private AsyncLogOption(Builder builder) {
        super(builder);
        this.discardingThreshold = builder.discardingThreshold;
        this.queueSize = builder.queueSize;
        this.neverBlock = builder.neverBlock;
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

    public static class Builder extends LogOption.Builder {
        private int discardingThreshold = 0;
        private int queueSize = 1024;
        private boolean neverBlock = true;

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

        // Overriding the build() method to return an instance of AsyncLogOption
        @Override
        public AsyncLogOption build() {
            return new AsyncLogOption(this);
        }
    }
}
