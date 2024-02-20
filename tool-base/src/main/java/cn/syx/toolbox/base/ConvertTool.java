package cn.syx.toolbox.base;

/**
 * 类型转换相关工具
 *
 * @author syx
 */
public class ConvertTool {

    private ConvertTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 转换到目标类型的对象
     *
     * @param obj 目标对象
     * @return T 目标类型的类
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(Object obj) {
        return (T) obj;
    }

    /**
     * 转换到目标类型的对象
     *
     * @param obj 目标对象
     * @param cls 目标类型的class
     * @return T 目标类型的类
     */
    public static <T> T convert(Object obj, Class<T> cls) {
        return cls.cast(obj);
    }
}
