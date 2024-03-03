package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

public class GrayFactory {

    private static final GrayFactory INSTANCE = new GrayFactory();

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
}
