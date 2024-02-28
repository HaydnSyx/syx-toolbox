package cn.syx.toolbox.gray.strategy;

import cn.syx.toolbox.gray.domain.GrayTask;
import cn.syx.toolbox.gray.strategy.option.ILoadTaskOption;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class LoadTaskStrategyManager {

    private LoadTaskStrategy INSTANCE;

    private volatile boolean inited = false;

    public void init(ILoadTaskOption option) {
        if (Objects.nonNull(INSTANCE)) {
            return;
        }

        synchronized (this) {
            if (inited && Objects.nonNull(INSTANCE)) {
                return;
            }
            Class<? extends LoadTaskStrategy> cls = option.getStrategyClass();
            // 实例化类
            try {
                INSTANCE = cls.getDeclaredConstructor().newInstance(option);
                inited = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List<GrayTask> loadAllTask() {
        if (!inited) {
            throw new RuntimeException("LoadTaskStrategyManager not init");
        }

        return INSTANCE.loadAllTask();
    }
}
