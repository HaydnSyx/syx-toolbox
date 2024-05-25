package cn.syx.toolbox.base;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.io.*;
import java.util.Objects;

/**
 * 文件相关工具
 *
 * @author syx
 */
public class FileToolTest {

    private static final String TEST_CONTENT = "{\"name\":\"syx\", \"age\": 18}";

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(ConvertTool.class));
    }

    @Test
    public void testReadContentWithResource() {
        String content = FileTool.readContentWithResource(FileToolTest.class, "");
        Assertions.assertNull(content);
        content = FileTool.readContentWithResource(FileToolTest.class, "123.json");
        Assertions.assertNull(content);
        content = FileTool.readContentWithResource(FileToolTest.class, "test.json");
        Assertions.assertEquals(TEST_CONTENT, content);
    }
}
