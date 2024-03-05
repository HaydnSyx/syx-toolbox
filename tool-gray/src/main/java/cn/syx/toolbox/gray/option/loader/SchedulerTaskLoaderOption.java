package cn.syx.toolbox.gray.option.loader;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

import java.util.concurrent.TimeUnit;

public abstract class SchedulerTaskLoaderOption extends CommonTaskLoaderOption {

    private long initialDelay;
    private long period;
    private TimeUnit timeUnit;

    protected SchedulerTaskLoaderOption(Class<? extends TaskLoader> cls) {
        super(cls);
    }

    protected SchedulerTaskLoaderOption(Builder<?> builder, Class<? extends TaskLoader> cls) {
        this(cls);
        this.initialDelay = builder.initialDelay;
        this.period = builder.period;
        this.timeUnit = builder.timeUnit;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public long getPeriod() {
        return period;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected long initialDelay = 0L;
        protected long period = 5L;
        protected TimeUnit timeUnit = TimeUnit.SECONDS;

        public T initialDelay(long initialDelay) {
            this.initialDelay = initialDelay;
            return self();
        }

        public T period(long period) {
            this.period = period;
            return self();
        }

        public T timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return self();
        }

        abstract SchedulerTaskLoaderOption build();

        protected abstract T self();
    }
}
