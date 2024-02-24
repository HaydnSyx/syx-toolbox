package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

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
}
