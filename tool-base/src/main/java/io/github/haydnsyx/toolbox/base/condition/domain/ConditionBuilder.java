package io.github.haydnsyx.toolbox.base.condition.domain;

import io.github.haydnsyx.toolbox.base.condition.impl.SimpleCondition;
import io.github.haydnsyx.toolbox.base.condition.Condition;
import io.github.haydnsyx.toolbox.base.condition.enums.CompareEnum;
import io.github.haydnsyx.toolbox.base.condition.impl.GroupCondition;

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
        conditions.add(new SimpleCondition(key, compareEnum, value));
        this.isOr = true;
        return this;
    }

    public ConditionBuilder and(ConditionBuilder builder) {
        conditions.add(builder.build());
        this.isOr = false;
        return this;
    }

    public ConditionBuilder or(ConditionBuilder builder) {
        conditions.add(builder.build());
        this.isOr = true;
        return this;
    }

    public Condition build() {
        return new GroupCondition(conditions, isOr);
    }
}
