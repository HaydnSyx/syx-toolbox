package cn.syx.toolbox.base.proxy;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class JavassistProxy {

    private JavassistProxy() {
        throw new UnsupportedOperationException();
    }

    public static Object getProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
    }
}
