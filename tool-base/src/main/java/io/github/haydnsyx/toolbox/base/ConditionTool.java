package io.github.haydnsyx.toolbox.base;

import io.github.haydnsyx.toolbox.base.condition.Condition;
import io.github.haydnsyx.toolbox.base.condition.parse.Parser;

import java.util.Objects;

public class ConditionTool {

    private ConditionTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 把表达式解析成对应的Condition结构
     *
     * @param str 表达式字符串
     * @return Condition
     */
    public static Condition parse(String str) {
        if (StringTool.isBlank(str)) {
            return null;
        }

        if (!ExpressionTool.isParenthesesBalanced(str)) {
            throw new RuntimeException("表达式中括号不匹配");
        }

        return Parser.parse(str);
    }

    /**
     * 将Condition结构翻译成对应的表达式
     *
     * @param condition 表达式结构
     * @return String
     */
    public static String translate(Condition condition) {
        if (Objects.isNull(condition)) {
            return StringTool.EMPTY;
        }

        StringBuilder expression = new StringBuilder();
        String result = Parser.generateStr(condition, expression);
        return ExpressionTool.findOutermostBracketsContent(result);
    }
}
