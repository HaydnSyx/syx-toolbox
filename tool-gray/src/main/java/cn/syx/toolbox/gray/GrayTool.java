package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.domain.GrayCondition;
import cn.syx.toolbox.gray.domain.GrayTask;

import java.util.List;

public class GrayTool {

    private GrayTool() {
        throw new UnsupportedOperationException();
    }

    public static boolean hitGray(GrayCondition condition) {
        List<GrayTask> tasks = GrayManager.getInstance().getTasks();
        return false;
    }
}