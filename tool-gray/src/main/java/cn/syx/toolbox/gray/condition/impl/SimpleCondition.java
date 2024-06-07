package cn.syx.toolbox.gray.condition.impl;

import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.enums.CompareEnum;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class SimpleCondition implements Condition {
    private final String key;
    private final CompareEnum compareEnum;
    private final Object value;

    public SimpleCondition(String key, CompareEnum compareEnum, Object value) {
        this.key = key;
        this.compareEnum = compareEnum;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public CompareEnum getCompareEnum() {
        return compareEnum;
    }

    public Object getValue() {
        return value;
    }

    // 根据具体情况实现比较逻辑
    @Override
    public boolean evaluate(Map<String, ?> data) {
        Object val = data.get(key);
        if (Objects.isNull(val)) {
            return false;
        }

        int result = Integer.MIN_VALUE;

        // 传入数据与配置数据比较
        if (val instanceof String v1 && value instanceof String v2) {
            result = v1.compareTo(v2);
        } else if (val instanceof Character v1 && value instanceof String v2) {
            result = v1.toString().compareTo(v2);
        } else if (val instanceof Number v1 && value instanceof String v2) {
            result = v1.toString().compareTo(v2);
        } else if (val instanceof Number v1 && value instanceof Number v2) {
            result = new BigDecimal(v1.toString()).compareTo(new BigDecimal(v2.toString()));
        }

        if (result == Integer.MIN_VALUE) {
            return false;
        }

        return switch (compareEnum) {
            case EQUALS -> result == 0;
            case LESS_THAN -> result < 0;
            case LESS_THAN_OR_EQUAL -> result <= 0;
            case GREATER_THAN -> result > 0;
            case GREATER_THAN_OR_EQUAL -> result >= 0;
            default -> false;
        };
    }
}
