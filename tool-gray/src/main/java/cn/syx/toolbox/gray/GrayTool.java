package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.domain.GrayRequest;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.matcher.GrayMatcher;
import cn.syx.toolbox.gray.option.GrayOption;

public class GrayTool {

    private GrayTool() {
        throw new UnsupportedOperationException();
    }

    public static void initManager(GrayOption option) {
        GrayManager.getInstance().init(option);
    }

    public static boolean hitGray(GrayRequest req) {
        GrayManager grayManager = GrayManager.getInstance();
        // 查找灰度任务
        GrayTaskConfig task = grayManager.getTask(req.identity());
        // 获取匹配器
        GrayMatcher matcher = grayManager.getMatcher(req.identity());
        return matcher.match(req, task);
    }
}