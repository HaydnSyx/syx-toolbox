package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.beans.JavaBean;
import java.io.Serial;
import java.lang.annotation.Documented;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Class相关工具
 *
 * @author syx
 */
public class ClassToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(ClassTool.class));
    }

    @Test
    public void testExistClass() {
        Assertions.assertTrue(ClassTool.existClass("java.lang.String"));
        Assertions.assertFalse(ClassTool.existClass("java.abc.Test"));
    }

    @Test
    public void testFindAnnotation() {
        JavaBean annotation1 = ClassTool.findAnnotation(ClassUnitTest.class, JavaBean.class);
        Assertions.assertNotNull(annotation1);

        Serial annotation2 = ClassTool.findAnnotation(ClassUnitTest.class, Serial.class);
        Assertions.assertNull(annotation2);
    }

    @Test
    public void testFindAnnotationField() {
        List<Field> fields= ClassTool.findAnnotationField(ClassUnitTest.class, Deprecated.class);
        Assertions.assertNotNull(fields);
        Assertions.assertEquals(1, fields.size());
    }

    @JavaBean
    public class ClassUnitTest {

        @Deprecated
        private String name;
        private int age;

    }
}
