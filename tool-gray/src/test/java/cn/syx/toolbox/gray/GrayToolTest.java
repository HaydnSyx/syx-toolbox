package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.domain.GrayRequest;
import cn.syx.toolbox.gray.key.impl.IntegerGrayKey;
import cn.syx.toolbox.gray.key.impl.StringGrayKey;
import cn.syx.toolbox.gray.option.GrayOption;
import cn.syx.toolbox.gray.option.loader.FileTaskLoaderOption;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class GrayToolTest {

    @Before
    public void init() {
        GrayOption option = GrayOption.builder()
                .loadTaskOption(FileTaskLoaderOption.builder()
                        .filePath(GrayToolTest.class.getClassLoader().getResource("gray").getPath())
                        .build())
                .build();
        GrayTool.initManager(option);
    }

    @Test
    public void testHitGray() {
        GrayRequest request = GrayRequest.builder()
                .taskGroup("taskGroup")
                .taskId("taskId")
                .key(StringGrayKey.of("tid"))
                .whiteBlackKey(IntegerGrayKey.of(123456))
                .build();
        boolean result = GrayTool.hitGray(request);
        Assert.assertTrue(result);

        request = GrayRequest.builder()
                .taskGroup("taskGroup")
                .taskId("taskId")
                .key(StringGrayKey.of("tid"))
                .whiteBlackKey(IntegerGrayKey.of(222222))
                .build();
        result = GrayTool.hitGray(request);
        Assert.assertFalse(result);

        request = GrayRequest.builder()
                .taskGroup("taskGroup")
                .taskId("taskId")
                .key(StringGrayKey.of("tid"))
                .build();
        result = GrayTool.hitGray(request);
        Assert.assertTrue(result);

        request = GrayRequest.builder()
                .taskGroup("taskGroup")
                .taskId("taskId")
                .key(StringGrayKey.of("tid"))
                .conditions(Map.of(
                        "name", "syx",
                        "age", 18,
                        "version", "1.0.0"
                )).build();
        result = GrayTool.hitGray(request);
        Assert.assertFalse(result);
    }

    @Test
    public void testHitGray2() {
        GrayRequest request = GrayRequest.builder()
                .taskGroup("taskGroup")
                .taskId("taskId")
                .key(StringGrayKey.of("tid"))
                .conditions(Map.of(
                        "name", "syx",
                        "age", 18,
                        "version", "1.0.1"
                )).build();
        boolean result = GrayTool.hitGray(request);
        Assert.assertTrue(result);
    }
}