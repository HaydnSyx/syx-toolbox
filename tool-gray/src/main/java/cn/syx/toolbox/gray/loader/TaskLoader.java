package cn.syx.toolbox.gray.loader;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

import java.util.List;

public interface TaskLoader<Op extends TaskLoaderOption> {

    void parseOption(Op option);

    List<GrayTaskConfig> loadAllTask();

    void start(GrayTaskHolder holder);
}
