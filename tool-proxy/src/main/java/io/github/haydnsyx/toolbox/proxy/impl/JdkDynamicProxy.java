package io.github.haydnsyx.toolbox.proxy.impl;

import io.github.haydnsyx.toolbox.proxy.IProxy;

import java.lang.reflect.Proxy;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class JdkDynamicProxy implements IProxy {

    public Object generateProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if (interfaces.length == 0) {
            throw new RuntimeException("目标类没有接口，无法使用jdk动态代理");
        }

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    before.accept(args);
                    Object result = method.invoke(target, args);
                    after.accept(args, result);
                    return result;
                }
        );
    }
}
