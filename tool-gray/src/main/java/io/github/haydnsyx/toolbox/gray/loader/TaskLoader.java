package io.github.haydnsyx.toolbox.gray.loader;

import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskHolder;
import io.github.haydnsyx.toolbox.gray.option.TaskLoaderOption;

import java.util.List;

public interface TaskLoader<Op extends TaskLoaderOption> {

    void parseOption(Op option);

    List<GrayTaskConfig> loadAllTask();

    void start(GrayTaskHolder holder);
}
