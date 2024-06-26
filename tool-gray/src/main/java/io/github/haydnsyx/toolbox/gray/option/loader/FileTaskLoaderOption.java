package io.github.haydnsyx.toolbox.gray.option.loader;

import io.github.haydnsyx.toolbox.gray.loader.impl.FileTaskLoader;
import io.github.haydnsyx.toolbox.gray.resolver.TaskResolver;
import io.github.haydnsyx.toolbox.gray.resolver.impl.JsonTaskResolver;

public class FileTaskLoaderOption extends SchedulerTaskLoaderOption {

    private static final String FILE_TYPE_JSON = "json";

    private String filePath;

    private String fileType;

    // 文件解析器
    private TaskResolver taskResolver;

    private FileTaskLoaderOption(Builder builder) {
        super(builder, FileTaskLoader.class);
        this.filePath = builder.filePath;
        this.fileType = builder.fileType;
        this.taskResolver = builder.taskResolver;
    }

    public static Builder builder() {
        return new Builder();
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

    public static class Builder extends SchedulerTaskLoaderOption.Builder<Builder> {

        private String filePath;
        private String fileType = FILE_TYPE_JSON;
        private TaskResolver taskResolver = new JsonTaskResolver();

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder taskResolver(TaskResolver taskResolver) {
            this.taskResolver = taskResolver;
            return this;
        }

        @Override
        public FileTaskLoaderOption build() {
            return new FileTaskLoaderOption(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
