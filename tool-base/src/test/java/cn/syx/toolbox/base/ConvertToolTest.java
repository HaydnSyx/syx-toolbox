package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

/**
 * 类型转换相关工具
 *
 * @author syx
 */
public class ConvertToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(ConvertTool.class));
    }

    @Test
    public void testConvert() {
        Object obj = "123";
        String str = ConvertTool.convert(obj);
        Assertions.assertEquals("123", str);

        obj = 123;
        int i = ConvertTool.convert(obj);
        Assertions.assertEquals(123, i);

        obj = 123;
        Integer integer = ConvertTool.convert(obj, Integer.class);
        Assertions.assertEquals(123, integer);
    }
}
