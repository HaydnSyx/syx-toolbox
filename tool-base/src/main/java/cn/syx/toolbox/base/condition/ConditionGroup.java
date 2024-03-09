package cn.syx.toolbox.base.condition;

import java.util.ArrayList;
import java.util.List;

public class ConditionGroup implements Condition {

    private List<Condition> conditions = new ArrayList<>();
    private boolean isOr;

    public ConditionGroup() {
        this.isOr = false;
    }

    public ConditionGroup(List<Condition> conditions, boolean isOr) {
        this.conditions = conditions;
        this.isOr = isOr;
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    @Override
    public boolean evaluate(Object object) {
        for (Condition condition : conditions) {
            boolean result = condition.evaluate(object);
            if (isOr && result) {
                return true;
            } else if (!isOr && !result) {
                return false;
            }
        }
        return !isOr;
    }
}
