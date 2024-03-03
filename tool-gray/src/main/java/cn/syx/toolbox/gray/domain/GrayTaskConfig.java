package cn.syx.toolbox.gray.domain;

import cn.syx.toolbox.base.StringTool;

import java.util.Set;

public class GrayTaskConfig implements GrayIdentity {

    private String identity;

    private String taskGroup;

    private String taskId;

    private String taskDesc;
    /** 任务开关 */
    private boolean taskSwitch;
    /** 放量值（分子） */
    private int numerator;
    /** 基数值（分母） */
    private int denominator = 10000;

    /** 白名单 */
    private Set<?> whites;
    /** 黑名单 */
    private Set<?> blacks;
    // and and or and
    // value:key compare target
    /** todo 自定义场景 */
    private Set<Object> conditions;

    @Override
    public String identity() {
        if (StringTool.isBlank(this.identity)) {
            this.identity = String.format("%s_%s", taskGroup, taskId);
        }
        return this.identity;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public boolean open() {
        return taskSwitch;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Set<?> getWhites() {
        return whites;
    }

    public Set<?> getBlacks() {
        return blacks;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public void setTaskSwitch(boolean taskSwitch) {
        this.taskSwitch = taskSwitch;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}
