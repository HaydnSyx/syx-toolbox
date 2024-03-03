package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.matcher.GrayMatcher;
import cn.syx.toolbox.gray.matcher.impl.DefaultGrayMatcher;
import cn.syx.toolbox.gray.option.GrayOption;
import cn.syx.toolbox.gray.loader.TaskLoaderManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GrayManager {

    private static List<GrayTaskConfig> TASKS = new ArrayList<>();
    private static final Map<String, List<GrayTaskConfig>> GROUP_TASK_MAP = new HashMap<>();
    private static final Map<String, GrayTaskConfig> TASK_MAP = new HashMap<>();

    private static final Map<String, GrayMatcher> MATCHER_MAP = new HashMap<>();

    private static final GrayManager INSTANCE = new GrayManager();

    private volatile boolean inited;

    private GrayManager() {
    }

    public static GrayManager getInstance() {
        return INSTANCE;
    }

    public void init(GrayOption option) {
        // 创建任务holder
        GrayTaskHolder holder = GrayTaskHolder.getInstance();

        // 根据配置使用工厂创建出解析器


        // 根据配置使用工厂创建出任务加载器

        // 使用工厂创建出所有匹配器

        // 任务与匹配器封装到holder中国呢

        // 启动加载器（加载器内部处理推或拉的动作）


        synchronized (this) {
            // 初始化策略
            new TaskLoaderManager().init(option.getLoadTaskOption());

            // 是否需要加载任务
            if (!option.isDelayLoad()) {
                TASKS = new TaskLoaderManager().loadAllTask();
                GROUP_TASK_MAP.putAll(TASKS.stream().collect(Collectors.groupingBy(GrayTaskConfig::getTaskGroup)));
                TASKS.forEach(e -> {
                    String identity = e.identity();
                    TASK_MAP.put(identity, e);
                    MATCHER_MAP.put(identity, new DefaultGrayMatcher());
                });
            }

            // 启动定时获取任务


            // 启动监听任务

            // 初始化完成
            inited = true;
        }
    }

    // 返回指定组下所有任务
    public List<GrayTaskConfig> getTasks(String taskGroup) {
        return GROUP_TASK_MAP.get(taskGroup);
    }

    // 返回指定任务
    public GrayTaskConfig getTask(String identity) {
        return TASK_MAP.get(identity);
    }

    public GrayMatcher getMatcher(String identity) {
        return MATCHER_MAP.get(identity);
    }
}