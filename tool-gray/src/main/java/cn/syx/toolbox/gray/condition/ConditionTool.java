package cn.syx.toolbox.gray.condition;

import cn.syx.toolbox.gray.condition.impl.GroupCondition;
import cn.syx.toolbox.gray.condition.impl.SimpleCondition;
import cn.syx.toolbox.gray.condition.parse.Parser;

import java.util.Objects;

import static cn.syx.toolbox.gray.condition.enums.CompareEnum.*;

public class ConditionTool {

    private static final String BLANK = "";
    private static final String OR = " or ";
    private static final String AND = " and ";


    public static String transfer(Condition condition) {
        if (Objects.isNull(condition)) {
            return BLANK;
        }

        StringBuilder sb = new StringBuilder();
        return generateStr(condition, sb);
    }

    private static String generateStr(Condition condition, StringBuilder str) {
        if (condition instanceof GroupCondition group) {
            str.append("(");
            for (int i = 0; i < group.getConditions().size(); i++) {
                generateStr(group.getConditions().get(i), str);
                if (i != group.getConditions().size() - 1) {
                    str.append(group.isOr() ? OR : AND);
                }
            }
            str.append(")");
        } else if (condition instanceof SimpleCondition simple) {
            switch (simple.getCompareEnum()) {
                case EQUALS -> str.append(simple.getKey()).append(EQUALS.getCode()).append(simple.getValue());
                case LESS_THAN -> str.append(simple.getKey()).append(LESS_THAN.getCode()).append(simple.getValue());
                case LESS_THAN_OR_EQUAL -> str.append(simple.getKey()).append(LESS_THAN_OR_EQUAL.getCode()).append(simple.getValue());
                case GREATER_THAN -> str.append(simple.getKey()).append(GREATER_THAN.getCode()).append(simple.getValue());
                case GREATER_THAN_OR_EQUAL -> str.append(simple.getKey()).append(GREATER_THAN_OR_EQUAL.getCode()).append(simple.getValue());
                case IN -> str.append(simple.getKey()).append(IN.getCode()).append(simple.getValue());
                case NOT_IN -> str.append(simple.getKey()).append(NOT_IN.getCode()).append(simple.getValue());
            }
        }
        return str.toString();
    }

    public static Condition parse(String str) {
        return new Parser().parse(str);
    }
}
