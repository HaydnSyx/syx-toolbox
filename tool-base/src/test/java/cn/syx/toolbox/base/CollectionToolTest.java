package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(CollectionTool.class));
    }

    @Test
    public void test() {
        List<String> collection = null;
        Assertions.assertTrue(CollectionTool.isEmpty(collection));
        Assertions.assertFalse(CollectionTool.isNotEmpty(collection));

        collection = Collections.singletonList("123");
        Assertions.assertFalse(CollectionTool.isEmpty(collection));
        Assertions.assertTrue(CollectionTool.isNotEmpty(collection));
    }
}
