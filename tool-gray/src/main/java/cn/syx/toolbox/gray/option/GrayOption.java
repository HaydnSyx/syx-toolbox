package cn.syx.toolbox.gray.option;

import cn.syx.toolbox.gray.strategy.option.ILoadTaskOption;

public class GrayOption {

    private boolean delayLoad;

    private ILoadTaskOption loadTaskOption;

    public ILoadTaskOption getLoadTaskOption() {
        return loadTaskOption;
    }

    public boolean isDelayLoad() {
        return delayLoad;
    }
}
