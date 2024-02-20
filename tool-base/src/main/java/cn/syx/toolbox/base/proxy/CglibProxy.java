package cn.syx.toolbox.base.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CglibProxy {

    private CglibProxy() {
        throw new UnsupportedOperationException();
    }

    public static Object getProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, args, methodProxy) -> {
            before.accept(args);
            Object result = method.invoke(target, args);
            after.accept(args, result);
            return result;
        });
        return enhancer.create();
    }
}
