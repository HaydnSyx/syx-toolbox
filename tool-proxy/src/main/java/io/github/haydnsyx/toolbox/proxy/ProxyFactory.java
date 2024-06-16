package io.github.haydnsyx.toolbox.proxy;

import io.github.haydnsyx.toolbox.base.ClassTool;
import io.github.haydnsyx.toolbox.proxy.impl.*;

public class ProxyFactory {

    private static volatile ProxyFactory INSTANCE;

    private final IProxy proxyImpl;

    private ProxyFactory(IProxy proxy) {
        this.proxyImpl = proxy;
    }

    public IProxy getProxyImpl() {
        return proxyImpl;
    }

    public static IProxy getProxy() {
        return getInstance().getProxyImpl();
    }

    private static ProxyFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ProxyFactory.class) {
                if (INSTANCE == null) {
                    IProxy proxy = findProxyImpl();
                    INSTANCE = new ProxyFactory(proxy);
                }
            }
        }
        return INSTANCE;
    }

    private static IProxy findProxyImpl() {
        if (ClassTool.existClass("javassist.util.proxy.ProxyFactory")) {
            return new JavassistProxy();
        }

        if (ClassTool.existClass("net.bytebuddy.ByteBuddy")) {
            return new ByteBuddyProxy();
        }

        return new JdkDynamicProxy();
    }
}
