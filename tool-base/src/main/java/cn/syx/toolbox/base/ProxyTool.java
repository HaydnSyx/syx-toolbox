package cn.syx.toolbox.base;

import cn.syx.toolbox.base.proxy.ByteBuddyProxy;
import cn.syx.toolbox.base.proxy.CglibProxy;
import cn.syx.toolbox.base.proxy.JavassistProxy;
import cn.syx.toolbox.base.proxy.SpringProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
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
     * @param before 前置增强处理
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
     * @param after  后置增强处理
     * @return T 生成的代理类
     */
    public static <T> T getProxy(T target, BiConsumer<Object, Object> after) {
        return getProxy(target, P -> {}, after);
    }

    /**
     * 获取代理简易实现（环绕增强）
     *
     * @param target 代理的目标类
     * @param before 前置增强处理
     * @param after  后置增强处理
     * @return T 生成的代理类
     */
    public static <T> T getProxy(T target, Consumer<Object> before, BiConsumer<Object, Object> after) {
        Object object;
        // javassist动态代理实现
        if (ClassTool.existClass("javassist.util.proxy.ProxyFactory")) {
            try {
                object = JavassistProxy.getProxy(target, before, after);
            } catch (InstantiationException | IllegalAccessException
                     | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("无法使用javassist为目标类生成动态代理", e);
            }
        }
        // bytebuddy动态代理实现
        else if (ClassTool.existClass("net.bytebuddy.ByteBuddy")) {
            try {
                object = ByteBuddyProxy.getProxy(target, before, after);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("无法使用ByteBuddy为目标类生成动态代理", e);
            }
        }
        // spring aop动态代理
        else if (ClassTool.existClass("org.springframework.aop.framework.ProxyFactory")) {
            object = SpringProxy.getProxy(target, before, after);
        }
        // cglib动态代理
        else if (ClassTool.existClass("org.springframework.cglib.proxy.Enhancer")) {
            object = CglibProxy.getProxy(target, before, after);
        }
        // jdk动态代理实现
        else {
            Class<?>[] interfaces = target.getClass().getInterfaces();
            if (interfaces.length == 0) {
                throw new RuntimeException("目标类没有接口，无法使用jdk动态代理");
            }

            object = Proxy.newProxyInstance(
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

        return ConvertTool.convert(object);
    }
}
