package cn.syx.toolbox.base.retry;

import cn.syx.toolbox.base.exception.RetryException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RetryPolicy {

    public static final RetryPolicy DEFAULT = RetryPolicy.builder().build();

    private int retryNum;

    private long intervalTime;

    private TimeUnit timeUnit;

    private Class<? extends Throwable> retryOnThrow;

    private Class<? extends Throwable> degradeOnThrow;

    private RetryPolicy() {
    }

    public static Builder builder() {
        return new Builder();
    }

    // 构造者模式
    public static class Builder {
        private int retryNum = 3;
        private long intervalTime = 50;
        private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        private Class<? extends Throwable> retryOnThrow = RetryException.class;

        private Class<? extends Throwable> degradeOnThrow;

        public Builder retryNum(int retryNum) {
            this.retryNum = retryNum;
            return this;
        }

        public Builder intervalTime(long intervalTime) {
            this.intervalTime = intervalTime;
            return this;
        }

        public Builder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public Builder retryOnThrow(Class<? extends Throwable> retryOnThrow) {
            this.retryOnThrow = retryOnThrow;
            return this;
        }

        public Builder degradeOnThrow(Class<? extends Throwable> degradeOnThrow) {
            this.degradeOnThrow = degradeOnThrow;
            return this;
        }

        public RetryPolicy build() {
            RetryPolicy retryPolicy = new RetryPolicy();
            retryPolicy.retryNum = this.retryNum;
            retryPolicy.intervalTime = this.intervalTime;
            retryPolicy.timeUnit = this.timeUnit;
            retryPolicy.retryOnThrow = this.retryOnThrow;
            retryPolicy.degradeOnThrow = this.degradeOnThrow;
            return retryPolicy;
        }
    }

    public int getRetryNum() {
        return retryNum;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public boolean isRetryOnThrow(Throwable throwable) {
        return Objects.nonNull(this.retryOnThrow) && retryOnThrow.isInstance(throwable);
    }

    public boolean isDegradeOnThrow(Throwable throwable) {
        return Objects.nonNull(this.degradeOnThrow) && degradeOnThrow.isInstance(throwable);
    }
}
