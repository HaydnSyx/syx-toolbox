package cn.syx.toolbox.gray.strategy.impl;

import cn.syx.toolbox.gray.domain.GrayTask;
import cn.syx.toolbox.gray.strategy.LoadTaskStrategy;
import cn.syx.toolbox.gray.strategy.option.impl.FileLoadTaskOption;

import java.util.List;

public class FileLoadTaskStrategy implements LoadTaskStrategy {

    private FileLoadTaskOption taskOption;

    private String filePath;
    private String fileType;

    public FileLoadTaskStrategy(FileLoadTaskOption taskOption) {
        this.filePath = taskOption.getFilePath();
        this.fileType = taskOption.getFileType();
    }

    @Override
    public List<GrayTask> loadAllTask() {
        // 获取所有任务文件

        // 解析文件内容

        // 生成灰度任务

        return null;
    }

    // 根据filPath获取当前目录下所有文件
    public List<String> getAllFiles() {
        return null;
    }
}
