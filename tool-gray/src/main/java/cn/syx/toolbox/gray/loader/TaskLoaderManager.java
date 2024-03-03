package cn.syx.toolbox.gray.loader;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

import java.util.List;
import java.util.Objects;

public class TaskLoaderManager {

    private TaskLoader INSTANCE;

    private volatile boolean inited = false;

    public void init(TaskLoaderOption option) {
        if (Objects.nonNull(INSTANCE)) {
            return;
        }

        synchronized (this) {
            if (inited && Objects.nonNull(INSTANCE)) {
                return;
            }
            Class<? extends TaskLoader> cls = option.getTaskLoaderClass();
            // 实例化类
            try {
                INSTANCE = cls.getDeclaredConstructor().newInstance(option);
                inited = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List<GrayTaskConfig> loadAllTask() {
        if (!inited) {
            throw new RuntimeException("LoadTaskStrategyManager not init");
        }

        return INSTANCE.loadAllTask();
    }
}
