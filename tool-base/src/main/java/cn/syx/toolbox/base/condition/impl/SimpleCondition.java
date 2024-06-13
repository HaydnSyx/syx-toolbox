package cn.syx.toolbox.base.condition.impl;

import cn.syx.toolbox.base.condition.Condition;
import cn.syx.toolbox.base.condition.enums.CompareEnum;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SimpleCondition implements Condition {
    private final String key;
    private final CompareEnum compareEnum;
    private final Object value;

    public SimpleCondition(String key, CompareEnum compareEnum, Object value) {
        this.key = key;
        this.compareEnum = compareEnum;
        if (value instanceof Collection<?> col) {
            this.value = col.stream().map(Object::toString).collect(Collectors.toSet());
        } else {
            this.value = value;
        }
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

        if (value instanceof Collection<?> col) {
            if (CompareEnum.IN == compareEnum) {
                return Objects.nonNull(val) && col.contains(val.toString());
            }

            if (CompareEnum.NOT_IN == compareEnum) {
                return Objects.isNull(val) || !col.contains(val.toString());
            }
        }

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
