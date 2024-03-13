package cn.syx.toolbox.base;

import java.lang.reflect.Method;

/**
 * Class相关工具
 *
 * @author syx
 */
public class MethodTool {

    private MethodTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断是否为对象公共方法
     *
     * @param method 方法
     * @return boolean 是否为对象公共方法。true：是；false：否
     */
    public static boolean isLocalMethod(Method method) {
        return isLocalMethod(method.getName());
    }


    /**
     * 判断是否为对象公共方法
     *
     * @param methodName 方法名称
     * @return boolean 是否为对象公共方法。true：是；false：否
     */
    public static boolean isLocalMethod(final String methodName) {
        return "toSting".equals(methodName)
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
     * 生成方法签名
     *
     * @param method 方法
     * @return String 方法签名 例如：methodName(java.lang.String,java.lang.Integer)
     */
    public static String generateMethodSign(Method method) {
        StringBuilder sb = new StringBuilder(method.getName());
        sb.append("(");
        for (Class<?> parameterType : method.getParameterTypes()) {
            sb.append(parameterType.getCanonicalName()).append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }
}
