package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.domain.GrayTask;
import cn.syx.toolbox.gray.option.GrayOption;
import cn.syx.toolbox.gray.strategy.LoadTaskStrategyManager;

import java.util.ArrayList;
import java.util.List;

public class GrayManager {

    public static List<GrayTask> tasks = new ArrayList<>();

    private static final GrayManager INSTANCE = new GrayManager();

    private volatile boolean inited;

    private GrayManager() {
    }

    public static GrayManager getInstance() {
        return INSTANCE;
    }

    public void init(GrayOption option) {
        // 初始化策略
        new LoadTaskStrategyManager().init(option.getLoadTaskOption());

        // 是否需要加载任务
        if (!option.isDelayLoad()) {
            tasks = new LoadTaskStrategyManager().loadAllTask();
        }

        // 启动定时获取任务


        // 启动监听任务

    }


    // 返回所有任务
    public List<GrayTask> getTasks() {
        return tasks;
    }

    // 返回指定组下所有任务
    public List<GrayTask> getTasks(String taskGroup) {
        return tasks;
    }

    // 返回指定任务
    public GrayTask getTask(String taskGroup, String taskId) {
        return null;
    }
}