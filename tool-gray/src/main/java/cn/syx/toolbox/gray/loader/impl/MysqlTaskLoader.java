package cn.syx.toolbox.gray.loader.impl;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.option.loader.FileTaskLoaderOption;
import cn.syx.toolbox.gray.option.loader.MysqlTaskLoaderOption;

import java.util.List;

public class MysqlTaskLoader extends AbstractSchedulerTaskLoader {

    private MysqlTaskLoaderOption taskOption;

    public MysqlTaskLoader(FileTaskLoaderOption taskOption) {
        super();
    }

    @Override
    public List<GrayTaskConfig> loadAllTask() {
        // 获取所有任务文件

        // 解析文件内容

        // 生成灰度任务

        return null;
    }

    @Override
    public void syncTaskConfig(GrayTaskHolder holder) {

    }
}
