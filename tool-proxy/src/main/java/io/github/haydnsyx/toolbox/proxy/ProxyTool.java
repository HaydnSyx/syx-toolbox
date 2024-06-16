package io.github.haydnsyx.toolbox.proxy;

import io.github.haydnsyx.toolbox.base.ConvertTool;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 代理相关工具
 *
 * @author syx
 */
public class ProxyTool {

    private ProxyTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取代理简易实现（前置增强）
     *
     * @param target 代理的目标类
     * @param before 前置增强处理<参数>
     * @return T 生成的代理类
     */
    public static <T> T getProxy(T target, Consumer<Object> before) {
        return getProxy(target, before, (P, R) -> {
        });
    }

    /**
     * 获取代理简易实现（后置增强）
     *
     * @param target 代理的目标类
     * @param after  后置增强处理<参数, 结果>
     * @return T 生成的代理类
     */
    public static <T> T getProxy(T target, BiConsumer<Object, Object> after) {
        return getProxy(target, P -> {}, after);
    }

    /**
     * 获取代理简易实现（环绕增强）
     *
     * @param target 代理的目标类
     * @param before 前置增强处理<参数>
     * @param after  后置增强处理<参数, 结果>
     * @return T 生成的代理类
     */
    public static <T> T getProxy(T target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        Object object = ProxyFactory.getProxy().generateProxy(target, before, after);
        return ConvertTool.convert(object);
    }
}
