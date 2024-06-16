package io.github.haydnsyx.toolbox.gray.option;

import io.github.haydnsyx.toolbox.gray.loader.TaskLoader;

public interface TaskLoaderOption {

    Class<? extends TaskLoader> getTaskLoaderClass();
}
