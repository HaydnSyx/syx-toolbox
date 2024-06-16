package io.github.haydnsyx.toolbox.proxy.impl;

import io.github.haydnsyx.toolbox.proxy.IProxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ByteBuddyProxy implements IProxy {

    public Object generateProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        try (DynamicType.Unloaded<?> buddy = new ByteBuddy().subclass(target.getClass())
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(
                        (proxy, method, args) -> {
                            before.accept(args);
                            Object result = method.invoke(target, args);
                            after.accept(args, result);
                            return result;
                        })
                )
                .make()) {
            return buddy.load(target.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("无法使用ByteBuddy为目标类生成动态代理", e);
        }
    }
}
