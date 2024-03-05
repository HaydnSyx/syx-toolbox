package cn.syx.toolbox.gray;

import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.matcher.GrayMatcher;
import cn.syx.toolbox.gray.option.GrayOption;

import java.util.List;
import java.util.Map;

public class GrayManager {


    private static final GrayManager INSTANCE = new GrayManager();

    private final GrayTaskHolder holder;

    private volatile boolean inited;

    private GrayManager() {
        holder = GrayTaskHolder.getInstance();
    }

    public static GrayManager getInstance() {
        return INSTANCE;
    }

    public void init(GrayOption option) {
        if (inited) {
            return;
        }

        synchronized (this) {
            if (inited) {
                return;
            }

            // 创建任务工厂
            GrayFactory factory = GrayFactory.getInstance();

            // 根据配置使用工厂创建出解析器
            GrayMatcher commonMatcher = factory.crateGrayMatcher(option.getCommonMatcherCls());
            holder.addMatcher(null, commonMatcher);

            // 根据配置使用工厂创建出任务加载器
            TaskLoader taskLoader = factory.crateTaskLoader(option.getLoadTaskOption());

            // 使用工厂创建出所有匹配器
            Map<String, Class<? extends GrayMatcher>> matcherClassMap = option.getMatcherClsMap();
            matcherClassMap.forEach((k, v) -> {
                if (StringTool.isBlank(k)) {
                    return;
                }

                GrayMatcher matcher = factory.crateGrayMatcher(v);
                // 任务与匹配器封装到holder中国呢
                holder.addMatcher(k, matcher);
            });

            // 启动加载器（加载器内部处理推或拉的动作）
            taskLoader.start(holder);
            inited = true;
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