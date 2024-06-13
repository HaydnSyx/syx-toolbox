package cn.syx.toolbox.gray;

import cn.syx.toolbox.base.ExpressionTool;
import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.ConditionTool;
import cn.syx.toolbox.gray.condition.domain.ConditionBuilder;
import cn.syx.toolbox.gray.condition.enums.CompareEnum;
import com.sun.source.tree.ExpressionTree;
import org.junit.Test;

import java.util.*;

public class ConditionDemo {

    public static void main(String[] args) {
        Condition condition = ConditionBuilder.builder()
                .and("name", CompareEnum.EQUALS, "syx")
                .and(ConditionBuilder.builder()
                        .and(ConditionBuilder.builder()
                                .and("age", CompareEnum.GREATER_THAN_OR_EQUAL, 18)
                                .and("version", CompareEnum.GREATER_THAN, "1.0.0")
                        )
                        .or("age", CompareEnum.LESS_THAN_OR_EQUAL, 10)
                )
//                .and("type", CompareEnum.IN, Arrays.asList("1", "2", "3"))
                .build();

        String transfer = ConditionTool.translate(condition);
        System.out.println(transfer);
        System.out.println(ExpressionTool.removeInvalidParentheses(transfer));

        // 在这里使用条件进行判断
        boolean result = condition.evaluate(Map.of(
                "name", "syx",
                "age", 8,
                "version", "2.0.0"
        ));
        System.out.println("Evaluation result: " + result);

//        String s = "(name=syx and ((age>=18 and version>1.0.0) or (age<=10)))";
//        Condition condition = ConditionTool.parse(s);
//        System.out.println(1);
    }

    public static class Node {
        private Node parent;
        private String value;
        private String operator;
        private List<Node> children;

        public Node(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        Node(Node parent, String operator, String value) {
            this.parent = parent;
            this.operator = operator;
            this.value = value;
            this.children = new ArrayList<>();
        }
    }


    public Node parseExpression(String expression) {
        Node root = new Node(null, "and", null);
        parseExpression(root, expression);
        return root;
    }

    private void parseExpression(Node parent, String expression) {
        final char[] tokens = expression.toCharArray();
        StringBuilder tokenBuilder = new StringBuilder();
        String operate = "and";

        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            char c = tokens[i];
            tokenBuilder.append(c);
            if (i + 3 < length
                    && (tokens[i + 1] == 'a' || tokens[i + 1] == 'A')
                    && (tokens[i + 2] == 'n' || tokens[i + 2] == 'N')
                    && (tokens[i + 3] == 'd' || tokens[i + 3] == 'D')) {
                Node node = new Node(parent, operate, tokenBuilder.toString().trim());
                parent.children.add(node);
                tokenBuilder = new StringBuilder();
                operate = "and";
                i = i + 3;
            } else if (i + 2 < length
                    && (tokens[i + 1] == 'o' || tokens[i + 1] == 'O')
                    && (tokens[i + 2] == 'r' || tokens[i + 2] == 'R')) {
                Node node = new Node(parent, operate, tokenBuilder.toString().trim());
                tokenBuilder = new StringBuilder();
                parent.children.add(node);
                operate = "or";
                i = i + 2;
            } else if (c == '(') {
                // 查找括号内的内容
                String content = ExpressionTool.findOutermostBracketsContent(expression.substring(i));
                if (StringTool.isBlank(content)) {
                    continue;
                }

                Node node = new Node(parent, operate, null);
                parent.children.add(node);
                parseExpression(node, content);

                // 中间加的是空格，忽略
                tokenBuilder = new StringBuilder();
                i =  content.length() + i + 1;
                while (i < length && tokens[i] == ' ') {
                    i++;
                }
            }
        }

        if (!tokenBuilder.isEmpty()) {
            Node node = new Node(parent, operate, tokenBuilder.toString().trim());
            parent.children.add(node);
        }
    }



    @Test
    public void testTree() {
        String expression = "name=syx and (city = beijing or version >1.0.0)    and a>b or age < 10";
        ConditionDemo et = new ConditionDemo();
        Node root = et.parseExpression(expression);
        System.out.println(1);
    }
}
