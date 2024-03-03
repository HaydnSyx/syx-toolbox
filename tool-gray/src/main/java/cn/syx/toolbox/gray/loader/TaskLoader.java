package cn.syx.toolbox.gray.loader;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;

import java.util.List;

public interface TaskLoader {

    List<GrayTaskConfig> loadAllTask();

    void start(GrayTaskHolder holder);
}
