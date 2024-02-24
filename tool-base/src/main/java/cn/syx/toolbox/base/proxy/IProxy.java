package cn.syx.toolbox.base.proxy;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IProxy {

    /**
     * 为目标生成代理代理
     *
     * @param target 目标类
     * @param before 前置执行方法
     * @param after 后置执行方法
     * @return Object 生成的代理目标类
     */
    Object generateProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after);
}
