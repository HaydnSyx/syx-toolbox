package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.matcher.GrayMatcher;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrayFactory {

    private static final GrayFactory INSTANCE = new GrayFactory();

    private static final Set<Class<? extends GrayMatcher>> matcherClsSet = new HashSet<>();
    private static final Map<Class<? extends GrayMatcher>, GrayMatcher> matcherMap = new HashMap<>();

    private GrayFactory() {
    }

    public static GrayFactory getInstance() {
        return INSTANCE;
    }

    public TaskLoader crateTaskLoader(TaskLoaderOption option) {
        Class<? extends TaskLoader> cls = option.getTaskLoaderClass();
        // 实例化类
        try {
            TaskLoader taskLoader = cls.getDeclaredConstructor().newInstance();
            taskLoader.parseOption(option);
            return taskLoader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GrayMatcher crateGrayMatcher(Class<? extends GrayMatcher> matcherCls) {
        if (matcherClsSet.contains(matcherCls)) {
            return matcherMap.get(matcherCls);
        }

        // 实例化类
        try {
            GrayMatcher grayMatcher = matcherCls.getDeclaredConstructor().newInstance();
            matcherClsSet.add(matcherCls);
            matcherMap.put(matcherCls, grayMatcher);
            return grayMatcher;
        } catch (Exception e) {
            return null;
        }
    }
}
