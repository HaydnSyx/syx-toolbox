package cn.syx.toolbox.gray.domain;

import cn.syx.toolbox.gray.key.GrayKey;

public class GrayRequest implements GrayIdentity {

    private String taskGroup;

    private String taskId;

    private GrayKey<?> key;

    private GrayKey<?> whiteBlackKey;

    private GrayRequest() {
    }

    @Override
    public String identity() {
        return String.format("%s_%s", taskGroup, taskId);
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public String getTaskId() {
        return taskId;
    }

    public GrayKey<?> getKey() {
        return key;
    }

    public GrayKey<?> getWhiteBlackKey() {
        return this.whiteBlackKey;
    }
}
