package cn.syx.toolbox.gray.strategy.option.impl;

import cn.syx.toolbox.gray.strategy.LoadTaskStrategy;
import cn.syx.toolbox.gray.strategy.option.ILoadTaskOption;

public class FileLoadTaskOption implements ILoadTaskOption {

    @Override
    public Class<? extends LoadTaskStrategy> getStrategyClass() {
        return null;
    }

    private String filePath;

    private String fileType = "json";

    private FileLoadTaskOption(String filePath) {
        this.filePath = filePath;
    }

    private FileLoadTaskOption(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public static FileLoadTaskOption of(String filePath) {
        return new FileLoadTaskOption(filePath);
    }

    public static FileLoadTaskOption of(String filePath, String fileType) {
        return new FileLoadTaskOption(filePath, fileType);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }
}
