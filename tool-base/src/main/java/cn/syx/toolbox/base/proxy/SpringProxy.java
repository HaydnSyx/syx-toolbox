package cn.syx.toolbox.base.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SpringProxy {

    private SpringProxy() {
        throw new UnsupportedOperationException();
    }

    public static Object getProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        ProxyFactory pf = new ProxyFactory(target);
        pf.addAdvice((MethodInterceptor) method -> {
            before.accept(method.getArguments());
            Object result = method.proceed();
            after.accept(method.getArguments(), result);
            return result;
        });
        return pf.getProxy();
    }
}
