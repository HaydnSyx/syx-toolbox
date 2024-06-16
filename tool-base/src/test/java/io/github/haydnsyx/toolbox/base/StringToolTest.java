package io.github.haydnsyx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

public class StringToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(StringTool.class));
    }

    @Test
    public void test() {
        Assertions.assertTrue(StringTool.isEmpty(null));
        Assertions.assertTrue(StringTool.isEmpty(""));
        Assertions.assertFalse(StringTool.isEmpty("    "));
        Assertions.assertFalse(StringTool.isEmpty("123"));
        Assertions.assertFalse(StringTool.isEmpty("  123  "));

        Assertions.assertFalse(StringTool.isNotEmpty(null));
        Assertions.assertFalse(StringTool.isNotEmpty(""));
        Assertions.assertTrue(StringTool.isNotEmpty("    "));
        Assertions.assertTrue(StringTool.isNotEmpty("123"));
        Assertions.assertTrue(StringTool.isNotEmpty("  123  "));

        Assertions.assertTrue(StringTool.isBlank(null));
        Assertions.assertTrue(StringTool.isBlank(""));
        Assertions.assertTrue(StringTool.isBlank("    "));
        Assertions.assertFalse(StringTool.isBlank("123"));
        Assertions.assertFalse(StringTool.isBlank("  123  "));

        Assertions.assertFalse(StringTool.isNotBlank(null));
        Assertions.assertFalse(StringTool.isNotBlank(""));
        Assertions.assertFalse(StringTool.isNotBlank("    "));
        Assertions.assertTrue(StringTool.isNotBlank("123"));
        Assertions.assertTrue(StringTool.isNotBlank("  123  "));
    }
}
