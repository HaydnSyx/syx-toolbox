package cn.syx.toolbox.gray.option.loader;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.loader.impl.FileTaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;
import cn.syx.toolbox.gray.resolver.TaskResolver;
import cn.syx.toolbox.gray.resolver.impl.JsonTaskResolver;

public class FileTaskLoaderOption extends CommonTaskLoaderOption {

    private static final String FILE_TYPE_JSON = "json";

    private String filePath;

    private String fileType = "json";

    // 文件解析器
    private TaskResolver taskResolver;

    private FileTaskLoaderOption(String filePath, String fileType, TaskResolver taskResolver) {
        super(FileTaskLoader.class);
        this.filePath = filePath;
        this.fileType = fileType;
        this.taskResolver = taskResolver;
    }

    public static FileTaskLoaderOption of(String filePath) {
        return new FileTaskLoaderOption(filePath, FILE_TYPE_JSON, new JsonTaskResolver());
    }

    public static FileTaskLoaderOption of(String filePath, String fileType, TaskResolver taskResolver) {
        return new FileTaskLoaderOption(filePath, fileType, taskResolver);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public TaskResolver getTaskResolver() {
        return taskResolver;
    }
}
