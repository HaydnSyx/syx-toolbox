package cn.syx.toolbox.base.condition;

import java.util.ArrayList;
import java.util.List;

public class ConditionBuilder {

    private List<Condition> conditions = new ArrayList<>();
    private boolean isOr = false;

    public ConditionBuilder and(String key, CompareEnum compareEnum, Object value) {
        conditions.add(new SimpleCondition(key, compareEnum, value));
        this.isOr = false;
        return this;
    }

    public ConditionBuilder or(String key, CompareEnum compareEnum, Object value) {
        if (!isOr) {
            // 如果前一个条件不是OR，则创建一个新的条件组
            conditions.add(new ConditionGroup());
        }
        ConditionGroup group = (ConditionGroup) conditions.get(conditions.size() - 1);
        group.addCondition(new SimpleCondition(key, compareEnum, value));
        this.isOr = true;
        return this;
    }

    public ConditionBuilder and(ConditionBuilder builder) {
        conditions.add(builder.build());
        this.isOr = false;
        return this;
    }

    public ConditionBuilder or(ConditionBuilder builder) {
        if (!isOr) {
            conditions.add(new ConditionGroup());
        }
        ConditionGroup group = (ConditionGroup) conditions.get(conditions.size() - 1);
        group.addCondition(builder.build());
        this.isOr = true;
        return this;
    }

    public Condition build() {
        return new ConditionGroup(conditions, isOr);
    }
}
