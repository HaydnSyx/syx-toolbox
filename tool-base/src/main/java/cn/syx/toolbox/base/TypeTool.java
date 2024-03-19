package cn.syx.toolbox.base;

/**
 * Class相关工具
 *
 * @author syx
 */
public class TypeTool {

    private TypeTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 转换为基础类型对象，如果不是基础类型则返回原始对象
     *
     * @param origin 原始对象
     * @param clazz  目标类型
     * @return Object 转换类型后的对象
     */
    public static Object convertToBaseType(final Object origin, final Class<?> clazz) {
        if (origin instanceof Number) {
            if (clazz == int.class || clazz == Integer.class) {
                return ((Number) origin).intValue();
            } else if (clazz == long.class || clazz == Long.class) {
                return ((Number) origin).longValue();
            } else if (clazz == byte.class || clazz == Byte.class) {
                return ((Number) origin).byteValue();
            } else if (clazz == short.class || clazz == Short.class) {
                return ((Number) origin).shortValue();
            } else if (clazz == float.class || clazz == Float.class) {
                return ((Number) origin).floatValue();
            } else if (clazz == double.class || clazz == Double.class) {
                return ((Number) origin).doubleValue();
            }
        } else if (origin instanceof Boolean && (clazz == boolean.class || clazz == Boolean.class)) {
            return (Boolean) origin;
        } else if (origin instanceof Character && (clazz == char.class || clazz == Character.class)) {
            return (Character) origin;
        } else if (origin instanceof String && clazz == String.class) {
            return (String) origin;
        }

        return origin;
    }
}
