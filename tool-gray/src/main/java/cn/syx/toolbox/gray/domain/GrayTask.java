package cn.syx.toolbox.gray.domain;

public class GrayTask {

    private String taskGroup;

    private String taskId;

    private String taskDesc;

    private String key;

    private boolean taskSwitch;

    private int numerator;

    private int denominator = 10000;

    public String getTaskGroup() {
        return taskGroup;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getKey() {
        return key;
    }

    public boolean isTaskSwitch() {
        return taskSwitch;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}
