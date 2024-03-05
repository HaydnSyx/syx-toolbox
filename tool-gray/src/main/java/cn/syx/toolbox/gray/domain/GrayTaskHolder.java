package cn.syx.toolbox.gray.domain;

import cn.syx.toolbox.base.CollectionTool;
import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.gray.matcher.GrayMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GrayTaskHolder {

    private volatile Map<String, List<GrayTaskConfig>> GROUP_TASK_MAP = new HashMap<>();
    private volatile Map<String, GrayTaskConfig> TASK_MAP = new HashMap<>();
    private volatile Map<String, GrayMatcher> MATCHER_MAP = new HashMap<>();

    private GrayMatcher DEFAULT_MATCHER = null;

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

        Map<String, GrayTaskConfig> tmepMap = new HashMap<>();
        configs.forEach(e -> {
            String identity = e.identity();
            tmepMap.put(identity, e);
        });

        // 重新设置
        GROUP_TASK_MAP = configs.stream().collect(Collectors.groupingBy(GrayTaskConfig::getTaskGroup));
        TASK_MAP = tmepMap;
    }

    public GrayMatcher getMatcher(String identity) {
        GrayMatcher matcher = MATCHER_MAP.get(identity);
        return Objects.isNull(matcher) ? DEFAULT_MATCHER : matcher;
    }

    public void addMatcher(String id, GrayMatcher matcher) {
        if (StringTool.isBlank(id)) {
            this.DEFAULT_MATCHER = matcher;
        }

        this.MATCHER_MAP.put(id, matcher);
    }
}
