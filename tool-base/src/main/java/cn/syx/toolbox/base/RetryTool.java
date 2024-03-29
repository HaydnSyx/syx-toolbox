package cn.syx.toolbox.base;

import cn.syx.toolbox.base.retry.RetryPolicy;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 重试相关工具
 *
 * @author syx
 */
public class RetryTool {

    private RetryTool() {
        throw new UnsupportedOperationException();
    }

    public static <T> T execute(Supplier<T> retryExecute) {
        return execute(RetryPolicy.DEFAULT, retryExecute, () -> null);
    }

    public static <T> T execute(Supplier<T> retryExecute, Supplier<T> degradeExecute) {
        return execute(RetryPolicy.DEFAULT, retryExecute, degradeExecute);
    }

    public static <T> T execute(RetryPolicy policy, Supplier<T> retryExecute, Supplier<T> degradeExecute) {
        if (Objects.isNull(retryExecute)) {
            return null;
        }

        if (Objects.isNull(policy)) {
            return retryExecute.get();
        }

        int retryNum = policy.getRetryNum();
        do {
            try {
                return retryExecute.get();
            } catch (Throwable e) {
                // 休息一段时间再重试
                if (policy.isRetryOnThrow(e)) {
                    ThreadTool.sleep(policy.getIntervalTime(), policy.getTimeUnit());
                }
                // 降级处理
                else if (policy.isDegradeOnThrow(e)) {
                    if (Objects.nonNull(degradeExecute)) {
                        return degradeExecute.get();
                    }
                    return null;
                }
                // 抛出异常
                else {
                    throw e;
                }
            }
        } while (retryNum-- > 0);

        if (Objects.nonNull(degradeExecute)) {
            return degradeExecute.get();
        }

        return null;
    }
}
