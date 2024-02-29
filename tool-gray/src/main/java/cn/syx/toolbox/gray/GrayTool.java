package cn.syx.toolbox.gray;

import cn.syx.toolbox.base.NumberTool;
import cn.syx.toolbox.gray.domain.GrayRequest;
import cn.syx.toolbox.gray.domain.GrayTask;

import java.util.Objects;

public class GrayTool {

    private GrayTool() {
        throw new UnsupportedOperationException();
    }

    public static boolean hitGray(GrayRequest req) {
        GrayTask task = GrayManager.getInstance().getTask(req.getTaskGroup(), req.getTaskId());
        return check(req, task);
    }

    public static boolean check(GrayRequest req, GrayTask task) {
        if (Objects.isNull(task)) {
            return false;
        }

        // 如果任务未开启则不命中
        if (task.isTaskSwitch()) {
            return false;
        }

        // 如果未放量且未命中白名单则不命中

        // 如果条件不匹配则不命中

        // 如果比例不通则不命中
        int value = NumberTool.abs(req.getKey().hashCode()) % task.getDenominator();

        return value < task.getNumerator();
    }
}