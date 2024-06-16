package io.github.haydnsyx.toolbox.base.retry;

import io.github.haydnsyx.toolbox.base.exception.RetryException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RetryPolicy {

    /** 重试次数 */
    private int retryNum;

    /** 间隔时间 */
    private long intervalTime;

    /** 间隔时间单位 */
    private TimeUnit timeUnit;

    /** 重试指定异常 */
    private Throwable retryOnThrow;

    /** 降级指定异常 */
    private Throwable degradeOnThrow;

    private RetryPolicy() {
    }

    public static Builder builder() {
        return new Builder();
    }

    // 构造者模式
    public static class Builder {
        private int retryNum = 2;
        private long intervalTime = 50;
        private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        private Throwable retryOnThrow = new RetryException();

        private Throwable degradeOnThrow;

        public Builder retryNum(int retryNum) {
            this.retryNum = retryNum;
            if (this.retryNum <= 0) {
                this.retryNum = 0;
            }
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

        public Builder retryOnThrow(Throwable retryOnThrow) {
            this.retryOnThrow = retryOnThrow;
            return this;
        }

        public Builder degradeOnThrow(Throwable degradeOnThrow) {
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
        return Objects.nonNull(this.retryOnThrow) && retryOnThrow.getClass().isInstance(throwable);
    }

    public boolean isDegradeOnThrow(Throwable throwable) {
        return Objects.nonNull(this.degradeOnThrow) && degradeOnThrow.getClass().isInstance(throwable);
    }
}
