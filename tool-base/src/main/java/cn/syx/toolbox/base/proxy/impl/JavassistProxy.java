package cn.syx.toolbox.base.proxy.impl;

import cn.syx.toolbox.base.proxy.IProxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class JavassistProxy implements IProxy {

    public Object generateProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        try {
            ProxyFactory pf = new ProxyFactory();
            pf.setSuperclass(target.getClass());
            Class<?> proxyClass = pf.createClass();
            Object proxy = proxyClass.getDeclaredConstructor().newInstance();
            ((ProxyObject) proxy).setHandler((o, method, proceed, args) -> {
                before.accept(args);
                Object result = method.invoke(target, args);
                after.accept(args, result);
                return result;
            });
            return proxy;
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("无法使用javassist为目标类生成动态代理", e);
        }
    }
}
