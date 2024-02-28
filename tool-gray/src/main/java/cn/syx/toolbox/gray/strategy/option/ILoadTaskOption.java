package cn.syx.toolbox.gray.strategy.option;

import cn.syx.toolbox.gray.strategy.LoadTaskStrategy;

public interface ILoadTaskOption {

    Class<? extends LoadTaskStrategy> getStrategyClass();
}
