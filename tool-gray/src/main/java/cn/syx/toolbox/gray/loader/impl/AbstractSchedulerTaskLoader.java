package cn.syx.toolbox.gray.loader.impl;

import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractSchedulerTaskLoader<Op extends TaskLoaderOption> implements TaskLoader<Op> {

    private final ScheduledExecutorService executor;

    private volatile boolean inited;

    public AbstractSchedulerTaskLoader() {
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public abstract void syncTaskConfig(GrayTaskHolder holder);

    @Override
    public void start(GrayTaskHolder holder) {
        if (inited) {
            return;
        }

        inited = true;
        executor.scheduleAtFixedRate(() -> {
            // 加载任务
            syncTaskConfig(holder);
        }, 1, 1, TimeUnit.SECONDS);
    }
}
