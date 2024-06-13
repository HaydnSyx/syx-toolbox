package cn.syx.toolbox.base.condition.parse;

import cn.syx.toolbox.base.CollectionTool;
import cn.syx.toolbox.base.ExpressionTool;
import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.base.condition.domain.ConditionBuilder;
import cn.syx.toolbox.base.condition.enums.CompareEnum;
import cn.syx.toolbox.base.condition.impl.GroupCondition;
import cn.syx.toolbox.base.condition.impl.SimpleCondition;
import cn.syx.toolbox.base.condition.Condition;
import cn.syx.toolbox.base.condition.domain.ConditionNode;
import cn.syx.toolbox.base.condition.enums.TokenEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private static final char left_par = StringTool.LEFT_PARENTHESIS.charAt(0);

    public static Condition parse(String expression) {
        try {
            // 解析成语法树
            ConditionNode root = parseExpression(expression);

            // 构建目标的condition
            ConditionBuilder condition = ConditionBuilder.builder();
            buildCondition(root, condition);

            // 返回结果
            return condition.build();
        } catch (Exception e) {
            return null;
        }
    }

    public static ConditionNode parseExpression(String expression) {
        ConditionNode root = new ConditionNode(null, TokenEnum.AND, null);
        parseExpression(root, expression);
        return root;
    }

    private static void parseExpression(ConditionNode parent, String expression) {
        final char[] tokens = expression.toCharArray();
        StringBuilder tokenBuilder = new StringBuilder();
        TokenEnum operate = TokenEnum.AND;

        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            char c = tokens[i];
            tokenBuilder.append(c);
            if (i + 3 < length
                    && (tokens[i + 1] == 'a' || tokens[i + 1] == 'A')
                    && (tokens[i + 2] == 'n' || tokens[i + 2] == 'N')
                    && (tokens[i + 3] == 'd' || tokens[i + 3] == 'D')) {
                String content = tokenBuilder.toString();
                if (StringTool.isNotBlank(content)) {
                    ConditionNode conditionNode = new ConditionNode(parent, operate, content.trim());
                    parent.getChildren().add(conditionNode);
                }
                tokenBuilder = new StringBuilder();
                operate = TokenEnum.AND;
                i = i + 3;
            } else if (i + 2 < length
                    && (tokens[i + 1] == 'o' || tokens[i + 1] == 'O')
                    && (tokens[i + 2] == 'r' || tokens[i + 2] == 'R')) {
                String content = tokenBuilder.toString();
                if (StringTool.isNotBlank(content)) {
                    ConditionNode conditionNode = new ConditionNode(parent, operate, content.trim());
                    parent.getChildren().add(conditionNode);
                }
                tokenBuilder = new StringBuilder();
                operate = TokenEnum.OR;
                i = i + 2;
            } else {
                if (c == left_par) {
                    // 查找括号内的内容
                    String content = ExpressionTool.findOutermostBracketsContent(expression.substring(i));
                    if (StringTool.isBlank(content)) {
                        continue;
                    }

                    ConditionNode conditionNode = new ConditionNode(parent, operate, null);
                    parent.getChildren().add(conditionNode);
                    parseExpression(conditionNode, content);

                    tokenBuilder = new StringBuilder();
                    i = content.length() + i + 1;
                }
            }
        }

        if (!tokenBuilder.isEmpty()) {
            ConditionNode conditionNode = new ConditionNode(parent, operate, tokenBuilder.toString().trim());
            parent.getChildren().add(conditionNode);
        }
    }

    private static void buildCondition(ConditionNode node, ConditionBuilder builder) {
        if (CollectionTool.isEmpty(node.getChildren())) {
            if (TokenEnum.AND == node.getOperator()) {
                Token token = disassembly(node.getValue());
                builder.and(token.getKey(), token.getCompare(), Objects.isNull(token.getValue()) ? token.getValues() : token.getValue());
            } else if (TokenEnum.OR == node.getOperator()) {
                Token token = disassembly(node.getValue());
                builder.or(token.getKey(), token.getCompare(), Objects.isNull(token.getValue()) ? token.getValues() : token.getValue());
            } else {
                throw new RuntimeException("暂不支持该操作符: " + node.getOperator());
            }
        } else {
            ConditionBuilder subBuilder = ConditionBuilder.builder();
            for (ConditionNode child : node.getChildren()) {
                buildCondition(child, Objects.isNull(node.getParent()) ? builder : subBuilder);
            }

            if (Objects.nonNull(node.getParent())) {
                if (TokenEnum.AND == node.getOperator()) {
                    builder.and(subBuilder);
                } else if (TokenEnum.OR == node.getOperator()) {
                    builder.or(subBuilder);
                } else {
                    throw new RuntimeException("暂不支持该操作符: " + node.getOperator());
                }
            }
        }
    }

    private static Token disassembly(String content) {
        int index = content.indexOf(CompareEnum.NOT_IN.getCode());
        if (index > 0) {
            return getToken2(content, index, CompareEnum.NOT_IN);
        }

        index = content.indexOf(CompareEnum.IN.getCode());
        if (index > 0) {
            return getToken2(content, index, CompareEnum.IN);
        }

        index = content.indexOf(CompareEnum.GREATER_THAN_OR_EQUAL.getCode());
        if (index > 0) {
            return getToken(content, index, CompareEnum.GREATER_THAN_OR_EQUAL);
        }

        index = content.indexOf(CompareEnum.LESS_THAN_OR_EQUAL.getCode());
        if (index > 0) {
            return getToken(content, index, CompareEnum.LESS_THAN_OR_EQUAL);
        }

        index = content.indexOf(CompareEnum.GREATER_THAN.getCode());
        if (index > 0) {
            return getToken(content, index, CompareEnum.GREATER_THAN);
        }

        index = content.indexOf(CompareEnum.LESS_THAN.getCode());
        if (index > 0) {
            return getToken(content, index, CompareEnum.LESS_THAN);
        }

        index = content.indexOf(CompareEnum.EQUALS.getCode());
        if (index > 0) {
            return getToken(content, index, CompareEnum.EQUALS);
        }

        throw new RuntimeException("未解析出该内容: " + content);
    }

    private static Token getToken(String content, int index, CompareEnum compareEnum) {
        String key = content.substring(0, index).trim();
        String value = content.substring(index + compareEnum.getCode().length()).trim();
        return new Token(key, value, compareEnum);
    }

    private static Token getToken2(String content, int index, CompareEnum compareEnum) {
        String key = content.substring(0, index).trim();
        String value = content.substring(index + compareEnum.getCode().length()).trim();
        Set<String> set = Arrays.stream(value.substring(1, value.length() - 1).split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
        return new Token(key, set, compareEnum);
    }

    private static class Token {
        private final String key;
        private String value;
        private Set<String> values;
        private final CompareEnum compare;

        public Token(String key, String value, CompareEnum compare) {
            this.key = key;
            this.value = value;
            this.compare = compare;
        }

        public Token(String key, Set<String> list, CompareEnum compare) {
            this.key = key;
            this.values = list;
            this.compare = compare;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public Set<String> getValues() {
            return values;
        }

        public CompareEnum getCompare() {
            return compare;
        }
    }


    public static String generateStr(Condition condition, StringBuilder str) {
        if (condition instanceof GroupCondition group) {
            str.append(TokenEnum.LEFT_PARENTHESIS.getCode());
            for (int i = 0; i < group.getConditions().size(); i++) {
                generateStr(group.getConditions().get(i), str);
                if (i != group.getConditions().size() - 1) {
                    str.append(group.isOr() ? StringTool.BLANK + TokenEnum.OR.getCode() + StringTool.BLANK
                            : StringTool.BLANK + TokenEnum.AND.getCode() + StringTool.BLANK);
                }
            }
            str.append(TokenEnum.RIGHT_PARENTHESIS.getCode());
        } else if (condition instanceof SimpleCondition simple) {
            switch (simple.getCompareEnum()) {
                case EQUALS -> str.append(simple.getKey()).append(CompareEnum.EQUALS.getCode()).append(simple.getValue());
                case LESS_THAN -> str.append(simple.getKey()).append(CompareEnum.LESS_THAN.getCode()).append(simple.getValue());
                case LESS_THAN_OR_EQUAL -> str.append(simple.getKey()).append(CompareEnum.LESS_THAN_OR_EQUAL.getCode()).append(simple.getValue());
                case GREATER_THAN -> str.append(simple.getKey()).append(CompareEnum.GREATER_THAN.getCode()).append(simple.getValue());
                case GREATER_THAN_OR_EQUAL -> str.append(simple.getKey()).append(CompareEnum.GREATER_THAN_OR_EQUAL.getCode()).append(simple.getValue());
                case IN -> str.append(simple.getKey()).append(CompareEnum.IN.getCode()).append(simple.getValue());
                case NOT_IN -> str.append(simple.getKey()).append(CompareEnum.NOT_IN.getCode()).append(simple.getValue());
            }
        }
        return str.toString();
    }
}
