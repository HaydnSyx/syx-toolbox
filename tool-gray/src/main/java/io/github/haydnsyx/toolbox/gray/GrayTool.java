package io.github.haydnsyx.toolbox.gray;

import io.github.haydnsyx.toolbox.base.StringTool;
import io.github.haydnsyx.toolbox.gray.domain.GrayRequest;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;
import io.github.haydnsyx.toolbox.gray.matcher.GrayMatcher;
import io.github.haydnsyx.toolbox.gray.option.GrayOption;

import java.util.Objects;

public class GrayTool {

    private GrayTool() {
        throw new UnsupportedOperationException();
    }

    public static void initManager(GrayOption option) {
        GrayManager.getInstance().init(option);
    }

    public static boolean hitGray(GrayRequest req) {
        if (Objects.isNull(req) || StringTool.isBlank(req.identity())) {
            return false;
        }

        GrayManager grayManager = GrayManager.getInstance();
        // 查找灰度任务
        GrayTaskConfig task = grayManager.getTask(req.identity());
        // 获取匹配器
        GrayMatcher matcher = grayManager.getMatcher(req.identity());
        return matcher.match(req, task);
    }
}