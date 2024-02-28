package cn.syx.toolbox.gray.strategy.option.impl;

import cn.syx.toolbox.gray.strategy.LoadTaskStrategy;
import cn.syx.toolbox.gray.strategy.option.ILoadTaskOption;

public class BaseLoadTaskOption implements ILoadTaskOption {

    @Override
    public Class<? extends LoadTaskStrategy> getStrategyClass() {
        return cls;
    }

    private final Class<? extends LoadTaskStrategy> cls;

    private BaseLoadTaskOption(Class<? extends LoadTaskStrategy> cls) {
        this.cls = cls;
    }

    public static BaseLoadTaskOption of(Class<? extends LoadTaskStrategy> cls) {
        return new BaseLoadTaskOption(cls);
    }
}
