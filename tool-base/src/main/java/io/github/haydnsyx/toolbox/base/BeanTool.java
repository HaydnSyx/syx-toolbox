package io.github.haydnsyx.toolbox.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BeanTool {

    private BeanTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 转换目标对象为Map结构
     *
     * @param obj 目标对象
     * @return Map<String, ?> Map结构
     */
    public static Map<String, ?> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (Objects.isNull(obj)) {
            return map;
        }

        Class<?> clazz = obj.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception ignored) {
        }
        return map;
    }
}