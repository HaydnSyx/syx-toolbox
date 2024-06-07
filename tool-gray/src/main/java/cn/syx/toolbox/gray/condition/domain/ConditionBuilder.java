package cn.syx.toolbox.gray.condition.domain;

import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.enums.CompareEnum;
import cn.syx.toolbox.gray.condition.impl.GroupCondition;
import cn.syx.toolbox.gray.condition.impl.SimpleCondition;

import java.util.ArrayList;
import java.util.List;

public class ConditionBuilder {

    private final List<Condition> conditions = new ArrayList<>();
    private boolean isOr = false;

    public static ConditionBuilder builder() {
        return new ConditionBuilder();
    }

    public ConditionBuilder and(String key, CompareEnum compareEnum, Object value) {
        conditions.add(new SimpleCondition(key, compareEnum, value));
        this.isOr = false;
        return this;
    }

    public ConditionBuilder or(String key, CompareEnum compareEnum, Object value) {
        if (!isOr) {
            // 如果前一个条件不是OR，则创建一个新的条件组
            conditions.add(new GroupCondition());
        }
        GroupCondition group = (GroupCondition) conditions.get(conditions.size() - 1);
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
            conditions.add(new GroupCondition());
        }
        GroupCondition group = (GroupCondition) conditions.get(conditions.size() - 1);
        group.addCondition(builder.build());
        this.isOr = true;
        return this;
    }

    public Condition build() {
        return new GroupCondition(conditions, isOr);
    }
}
