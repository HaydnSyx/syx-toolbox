package cn.syx.toolbox.gray.strategy.option.impl;

import cn.syx.toolbox.gray.strategy.LoadTaskStrategy;
import cn.syx.toolbox.gray.strategy.option.ILoadTaskOption;

public class MysqlLoadTaskOption implements ILoadTaskOption {

    @Override
    public Class<? extends LoadTaskStrategy> getStrategyClass() {
        return null;
    }

    private String url;

    private String username;

    private String password;

    private String db;

    private String table;
}
