package io.github.haydnsyx.toolbox.gray.domain;

import io.github.haydnsyx.toolbox.base.StringTool;
import io.github.haydnsyx.toolbox.base.condition.Condition;

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
    /** 自定义场景表达式 */
    private String conditionExpress;
    private Condition condition;

    @Override
    public String identity() {
        if (StringTool.isBlank(this.identity)) {
            this.identity = String.format("%s_%s", taskGroup, taskId);
        }
        return this.identity;
    }

    public boolean open() {
        return isTaskSwitch();
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

    public boolean isTaskSwitch() {
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

    public void setWhites(Set<?> whites) {
        this.whites = whites;
    }

    public void setBlacks(Set<?> blacks) {
        this.blacks = blacks;
    }

    public String getConditionExpress() {
        return conditionExpress;
    }

    public void setConditionExpress(String conditionExpress) {
        this.conditionExpress = conditionExpress;
    }

    public Condition getCondition() {
        return condition;
    }

    public void wrapperCondition(Condition condition) {
        this.condition = condition;
    }
}
