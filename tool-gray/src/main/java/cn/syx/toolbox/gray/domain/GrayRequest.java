package cn.syx.toolbox.gray.domain;

import cn.syx.toolbox.gray.key.GrayKey;

import java.util.Map;

public class GrayRequest implements GrayIdentity {

    private String taskGroup;

    private String taskId;

    private GrayKey<?> key;

    private GrayKey<?> whiteBlackKey;

    private Map<String, ?> conditions;

    private GrayRequest() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String taskGroup;
        private String taskId;
        private GrayKey<?> key;
        private GrayKey<?> whiteBlackKey;
        private Map<String, ?> conditions;

        public Builder taskGroup(String taskGroup) {
            this.taskGroup = taskGroup;
            return this;
        }

        public Builder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder key(GrayKey<?> key) {
            this.key = key;
            return this;
        }

        public Builder whiteBlackKey(GrayKey<?> whiteBlackKey) {
            this.whiteBlackKey = whiteBlackKey;
            return this;
        }

        public Builder conditions(Map<String, ?> conditions) {
            this.conditions = conditions;
            return this;
        }

        public GrayRequest build() {
            GrayRequest request = new GrayRequest();
            request.taskGroup = this.taskGroup;
            request.taskId = this.taskId;
            request.key = this.key;
            request.whiteBlackKey = this.whiteBlackKey;
            request.conditions = this.conditions;
            return request;
        }
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

    public Map<String, ?> getConditions() {
        return conditions;
    }
}
