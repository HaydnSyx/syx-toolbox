package cn.syx.toolbox.gray.loader.impl;

import cn.syx.toolbox.base.CollectionTool;
import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.domain.GrayTaskHolder;
import cn.syx.toolbox.gray.option.TaskLoaderOption;
import cn.syx.toolbox.gray.option.loader.FileTaskLoaderOption;
import com.alibaba.fastjson2.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileTaskLoader extends AbstractSchedulerTaskLoader<FileTaskLoaderOption> {

    private String filePath;
    private String fileType;

    private String fileSuffix;

    public FileTaskLoader() {
        super();
    }

    @Override
    public void parseOption(FileTaskLoaderOption option) {
        this.filePath = option.getFilePath();
        this.fileType = option.getFileType();
        this.fileSuffix = "." + fileType;
    }

    @Override
    public List<GrayTaskConfig> loadAllTask() {
        // 获取指定路径下所有任务文件
        List<File> files = getAllFilesByType();
        if (CollectionTool.isEmpty(files)) {
            return Collections.emptyList();
        }

        List<GrayTaskConfig> configs = new ArrayList<>(files.size());
        for (File file : files) {
            // 解析文件内容
            String content = readFileContent(file);
            if (StringTool.isBlank(content)) {
                continue;
            }

            // 生成灰度任务
            GrayTaskConfig taskConfig = JSON.parseObject(content, GrayTaskConfig.class);
            taskConfig.setTaskGroup(file.getName().split("\\.")[0]);
            configs.add(taskConfig);
        }

        return configs;
    }

    // 根据filPath获取当前目录下所有fileType类型的文件
    public List<File> getAllFilesByType() {
        try (Stream<Path> paths = Files.walk(Paths.get(this.filePath))) {
            return paths.map(Path::toFile)
                    .filter(f -> f.getName().endsWith(this.fileSuffix))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    // 读取文件内容
    public String readFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public void syncTaskConfig(GrayTaskHolder holder) {
        List<GrayTaskConfig> configs = loadAllTask();
        if (CollectionTool.isEmpty(configs)) {
            holder.clearTask();
        }

        holder.updateTask(configs);
    }
}
