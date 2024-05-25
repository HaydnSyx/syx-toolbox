package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Map;

public class MapToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(MapTool.class));
    }

    @Test
    public void test() {
        Map<String, String> map = null;
        Assertions.assertTrue(MapTool.isEmpty(map));
        Assertions.assertFalse(MapTool.isNotEmpty(map));
        map = Map.of("name", "syx", "age", "18");
        Assertions.assertTrue(MapTool.isNotEmpty(map));
        Assertions.assertFalse(MapTool.isEmpty(map));
    }
}
