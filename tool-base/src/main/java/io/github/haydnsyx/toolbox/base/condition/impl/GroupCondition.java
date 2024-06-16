package io.github.haydnsyx.toolbox.base.condition.impl;

import io.github.haydnsyx.toolbox.base.condition.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupCondition implements Condition {

    private List<Condition> conditions = new ArrayList<>();
    private final boolean isOr;

    public GroupCondition() {
        this.isOr = false;
    }

    public GroupCondition(List<Condition> conditions, boolean isOr) {
        this.conditions = conditions;
        this.isOr = isOr;
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public boolean isOr() {
        return isOr;
    }

    @Override
    public boolean evaluate(Map<String, ?> data) {
        try {
            for (Condition condition : conditions) {
                boolean result = condition.evaluate(data);
                if (isOr && result) {
                    return true;
                } else if (!isOr && !result) {
                    return false;
                }
            }
            return !isOr;
        } catch (Exception e) {
            // 解析失败按不匹配处理
            return false;
        }
    }
}
