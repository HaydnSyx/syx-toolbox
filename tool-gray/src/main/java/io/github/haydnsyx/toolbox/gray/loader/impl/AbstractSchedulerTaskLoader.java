package io.github.haydnsyx.toolbox.gray.loader.impl;

import io.github.haydnsyx.toolbox.gray.domain.GrayTaskHolder;
import io.github.haydnsyx.toolbox.gray.loader.TaskLoader;
import io.github.haydnsyx.toolbox.gray.option.loader.SchedulerTaskLoaderOption;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public abstract class AbstractSchedulerTaskLoader<Op extends SchedulerTaskLoaderOption> implements TaskLoader<Op> {

    private final ScheduledExecutorService executor;

    private Op option;

    private volatile boolean inited;

    public AbstractSchedulerTaskLoader() {
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public abstract void syncTaskConfig(GrayTaskHolder holder);


    @Override
    public void parseOption(Op option) {
        this.option = option;
    }

    @Override
    public void start(GrayTaskHolder holder) {
        if (inited) {
            return;
        }

        inited = true;
        executor.scheduleAtFixedRate(() -> {
            // 加载任务
            syncTaskConfig(holder);
        }, option.getInitialDelay(), option.getPeriod(), option.getTimeUnit());
    }
}
