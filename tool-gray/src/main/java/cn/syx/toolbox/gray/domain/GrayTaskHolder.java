package cn.syx.toolbox.gray.domain;

import cn.syx.toolbox.base.CollectionTool;
import cn.syx.toolbox.gray.matcher.GrayMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GrayTaskHolder {

    private volatile Map<String, List<GrayTaskConfig>> GROUP_TASK_MAP = new HashMap<>();
    private volatile Map<String, GrayTaskConfig> TASK_MAP = new HashMap<>();
    private volatile Map<String, GrayMatcher> MATCHER_MAP = new HashMap<>();

    private static final GrayTaskHolder INSTANCE = new GrayTaskHolder();

    private GrayTaskHolder() {
    }

    public static GrayTaskHolder getInstance() {
        return INSTANCE;
    }

    public void clearTask() {
        GROUP_TASK_MAP = new HashMap<>();
        TASK_MAP = new HashMap<>();
    }

    public List<GrayTaskConfig> getTasksByGroup(String taskGroup) {
        return GROUP_TASK_MAP.get(taskGroup);
    }

    public GrayTaskConfig getTask(String identity) {
        return TASK_MAP.get(identity);
    }

    public void updateTask(List<GrayTaskConfig> configs) {
        if (CollectionTool.isEmpty(configs)) {
            return;
        }

        // fixme 遍历方式处理所有任务
        // 重新设置
        GROUP_TASK_MAP.putAll(configs.stream().collect(Collectors.groupingBy(GrayTaskConfig::getTaskGroup)));
        configs.forEach(e -> {
            String identity = e.identity();
            TASK_MAP.put(identity, e);
        });
    }

    public GrayMatcher getMatcher(String identity) {
        return MATCHER_MAP.get(identity);
    }
}
