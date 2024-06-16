package io.github.haydnsyx.toolbox.base;

import java.lang.reflect.Method;

public class MethodTool {

    private MethodTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断是否为本地方法
     *
     * @param methodName 方法名
     * @return boolean 是否为本地方法。true：是；false：不是
     */
    public static boolean isLocalMethod(final String methodName) {
        return "toString".equals(methodName)
                || "hashCode".equals(methodName)
                || "equals".equals(methodName)
                || "getClass".equals(methodName)
                || "notify".equals(methodName)
                || "notifyAll".equals(methodName)
                || "wait".equals(methodName)
                || "finalize".equals(methodName)
                || "clone".equals(methodName);
    }

    /**
     * 判断是否为本地方法
     *
     * @param method 指定方法
     * @return boolean 是否为本地方法。true：是；false：不是
     */
    public static boolean isLocalMethod(Method method) {
        return isLocalMethod(method.getName());
    }
}
