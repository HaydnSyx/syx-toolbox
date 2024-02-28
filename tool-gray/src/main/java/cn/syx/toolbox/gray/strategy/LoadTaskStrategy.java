package cn.syx.toolbox.gray.strategy;

import cn.syx.toolbox.gray.domain.GrayTask;

import java.util.List;

public interface LoadTaskStrategy {

    List<GrayTask> loadAllTask();
}
