package io.github.haydnsyx.toolbox.gray;

import io.github.haydnsyx.toolbox.base.StringTool;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskHolder;
import io.github.haydnsyx.toolbox.gray.loader.TaskLoader;
import io.github.haydnsyx.toolbox.gray.matcher.GrayMatcher;
import io.github.haydnsyx.toolbox.gray.option.GrayOption;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GrayManager {


    private static final GrayManager INSTANCE = new GrayManager();

    private final GrayTaskHolder holder;

    private volatile boolean initialized;

    private GrayManager() {
        holder = GrayTaskHolder.getInstance();
    }

    public static GrayManager getInstance() {
        return INSTANCE;
    }

    public void init(GrayOption option) {
        if (initialized) {
            return;
        }

        synchronized (this) {
            if (initialized) {
                return;
            }

            try {
                // 创建任务工厂
                GrayFactory factory = GrayFactory.getInstance();

                // 根据配置使用工厂创建默认匹配器
                GrayMatcher commonMatcher = factory.crateGrayMatcher(option.getCommonMatcherCls());
                holder.addMatcher(null, commonMatcher);

                // 根据配置使用工厂创建出任务加载器
                TaskLoader<?> taskLoader = factory.crateTaskLoader(option.getLoadTaskOption());

                // 使用工厂创建出所有匹配器
                Map<String, Class<? extends GrayMatcher>> matcherClassMap = option.getMatcherClsMap();
                Optional.ofNullable(matcherClassMap).ifPresent(m -> m.forEach((k, v) -> {
                    if (StringTool.isBlank(k)) {
                        return;
                    }

                    GrayMatcher matcher = factory.crateGrayMatcher(v);
                    // 任务与匹配器封装到holder中国呢
                    holder.addMatcher(k, matcher);
                }));

                // 立即加载一次
                if (!option.isDelayLoad()) {
                    holder.updateTaskConfigInfo(taskLoader.loadAllTask());
                }

                // 启动加载器（加载器内部处理推或拉的动作）
                taskLoader.start(holder);
                initialized = true;
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    // 返回指定组下所有任务
    public List<GrayTaskConfig> getTasks(String taskGroup) {
        return holder.getTasksByGroup(taskGroup);
    }

    // 返回指定任务
    public GrayTaskConfig getTask(String identity) {
        return holder.getTask(identity);
    }

    public GrayMatcher getMatcher(String identity) {
        return holder.getMatcher(identity);
    }
}