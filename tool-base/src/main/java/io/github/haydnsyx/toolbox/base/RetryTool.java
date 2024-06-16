package io.github.haydnsyx.toolbox.base;

import io.github.haydnsyx.toolbox.base.retry.RetryPolicy;

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

    /**
     * 按照默认策略执行重试, 命中指定异常后后执行降级方法
     * 重试次数默认2次, 重试间隔默认50ms, 重试异常默认RetryException, 降级异常默认不处理
     *
     * @param retryExecute 方法执行体
     * @return T 执行结果
     */
    public static <T> T execute(Supplier<T> retryExecute) {
        return execute(RetryPolicy.builder().build(), retryExecute, () -> null);
    }

    /**
     * 按照指定策略执行重试, 命中指定异常后后执行降级方法
     * 重试间隔\重试次数\重试异常\降级异常 由策略决定
     *
     * @param policy 重试策略
     * @param retryExecute 方法执行体
     * @param degradeExecute 降级执行体
     * @return T 执行结果
     */
    public static <T> T execute(RetryPolicy policy, Supplier<T> retryExecute, Supplier<T> degradeExecute) {
        if (Objects.isNull(retryExecute)) {
            return null;
        }

        if (Objects.isNull(policy)) {
            return retryExecute.get();
        }

        int retryNum = policy.getRetryNum() + 1;
        for (int i = 0; i < retryNum; i++) {
            try {
                return retryExecute.get();
            } catch (Throwable e) {
                // 休息一段时间再重试
                if (policy.isRetryOnThrow(e)) {
                    if (i != retryNum - 1) {
                        ThreadTool.sleep(policy.getIntervalTime(), policy.getTimeUnit());
                    }
                    continue;
                }

                // 降级处理
                if (policy.isDegradeOnThrow(e) && Objects.nonNull(degradeExecute)) {
                    return degradeExecute.get();
                }
                // 抛出异常
                else {
                    throw e;
                }
            }
        }

        return null;
    }
}
