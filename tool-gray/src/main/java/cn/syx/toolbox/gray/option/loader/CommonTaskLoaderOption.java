package cn.syx.toolbox.gray.option.loader;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

public class CommonTaskLoaderOption implements TaskLoaderOption {

    @Override
    public Class<? extends TaskLoader> getTaskLoaderClass() {
        return cls;
    }

    private final Class<? extends TaskLoader> cls;

    protected CommonTaskLoaderOption(Class<? extends TaskLoader> cls) {
        this.cls = cls;
    }

    public static CommonTaskLoaderOption of(Class<? extends TaskLoader> cls) {
        return new CommonTaskLoaderOption(cls);
    }
}
