package cn.syx.toolbox.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class相关工具
 *
 * @author syx
 */
public class ClassTool {

    private ClassTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断是否存在目标类
     *
     * @param className 类名
     * @return boolean 是否存在目标类。true：存在；false：不存在
     */
    public static boolean existClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 查找类上指定的注解,如果没有找到则返回null
     *
     * @param cls 指定类
     * @param annotationCls 注解类
     * @return T 注解实例
     */
    public static <T extends Annotation> T findAnnotation(Class<?> cls, Class<T> annotationCls) {
        while (cls != null) {
            if (cls.isAnnotationPresent(annotationCls)) {
                return cls.getAnnotation(annotationCls);
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /**
     * 查找类中指定注解的字段, 没有找到则返回空列表
     *
     * @param cls 指定类
     * @param annotationCls 注解类
     * @return List<Field> 字段列表
     */
    public static List<Field> findAnnotationField(Class<?> cls, Class<? extends Annotation> annotationCls) {
        List<Field> result = new ArrayList<>();
        while (cls != null) {
            Field[] fields = cls.getDeclaredFields();
            if (fields.length == 0) {
                cls = cls.getSuperclass();
                continue;
            }

            result.addAll(Arrays.stream(fields)
                    .filter(e -> e.isAnnotationPresent(annotationCls))
                    .toList());
            cls = cls.getSuperclass();
        }
        return result;
    }
}
