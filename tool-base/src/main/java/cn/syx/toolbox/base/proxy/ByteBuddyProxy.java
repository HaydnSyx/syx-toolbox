package cn.syx.toolbox.base.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ByteBuddyProxy {

    private ByteBuddyProxy() {
        throw new UnsupportedOperationException();
    }

    public static Object getProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after)
            throws InstantiationException, IllegalAccessException {
        return new ByteBuddy().subclass(target.getClass())
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(
                        (proxy, method, args) -> {
                            before.accept(args);
                            Object result = method.invoke(target, args);
                            after.accept(args, result);
                            return result;
                        })
                )
                .make()
                .load(target.getClass().getClassLoader())
                .getLoaded()
                .newInstance();
    }
}
