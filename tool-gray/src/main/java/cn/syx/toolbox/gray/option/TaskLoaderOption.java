package cn.syx.toolbox.gray.option;

import cn.syx.toolbox.gray.loader.TaskLoader;

public interface TaskLoaderOption {

    Class<? extends TaskLoader> getTaskLoaderClass();
}
