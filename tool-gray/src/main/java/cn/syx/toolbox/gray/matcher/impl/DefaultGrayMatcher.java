package cn.syx.toolbox.gray.matcher.impl;

import cn.syx.toolbox.base.CollectionTool;
import cn.syx.toolbox.base.NumberTool;
import cn.syx.toolbox.gray.domain.GrayRequest;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.matcher.GrayMatcher;

import java.util.Objects;
import java.util.Set;

public class DefaultGrayMatcher implements GrayMatcher {
    @Override
    public boolean match(GrayRequest req, GrayTaskConfig task) {
        // 任务不存在则不命中
        if (Objects.isNull(task)) {
            return false;
        }

        // 如果任务未开启则不命中
        if (task.open()) {
            return false;
        }

        // 如果在黑白名单则不命中
        Set<?> blacks = task.getBlacks();
        if (CollectionTool.isNotEmpty(blacks) && blacks.contains(req.getWhiteBlackKey().getData())) {
            return false;
        }

        // 如果在白名单则命中
        Set<?> whites = task.getWhites();
        if (CollectionTool.isNotEmpty(whites) && whites.contains(req.getWhiteBlackKey().getData())) {
            return true;
        }

        // todo 如果条件不匹配则不命中


        // 如果比例不通则不命中
        int value = NumberTool.abs(req.getKey().getData().hashCode()) % task.getDenominator();
        return value < task.getNumerator();
    }
}
