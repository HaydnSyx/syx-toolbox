package cn.syx.toolbox.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 表达式相关工具
 *
 * @author syx
 */
public class ExpressionTool {

    private ExpressionTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断字符串中的括号是否成对
     * 括号支持种类: '()'、'{}'、'[]'
     *
     * @param str 目标字符串
     * @return boolean 括号是否成对。true：成对；false：不成对
     */
    public static boolean isParenthesesBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            // 推入左括号
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == '}' || ch == ']') {
                // 没有对应的左括号，则说明不成对
                if (stack.isEmpty()) {
                    return false;
                }

                // 弹出左括号，判断左右括号是否匹配
                char top = stack.pop();
                if (!isMatch(top, ch)) {
                    return false;
                }
            }
        }

        // 如果还有剩余则说明不匹配
        return stack.isEmpty();
    }

    private static boolean isMatch(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    /**
     * 去除表达式中重复的括号与无效的括号
     *
     *
     */
    public static String removeInvalidParentheses(String str) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }
        while (!stack.isEmpty()) {
            sb.deleteCharAt(stack.pop());
        }
        return sb.toString();
    }



    public static String findOutermostBracketsContent(String expression) {
        Stack<Integer> stack = new Stack<>();
        int leftBracketIndex = -1;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    leftBracketIndex = i; // 记录最外层左括号的位置
                }
                stack.push(i);
            } else if (expression.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    // 找到了与最外层左括号对应的右括号
                    return expression.substring(leftBracketIndex + 1, i);
                }
            }
        }
        return null; // 如果没有找到匹配的括号，返回null
    }

    public static List<String> findAllBracketsContent(String expression) {
        List<String> results = new ArrayList<>();
        String outermostContent = findOutermostBracketsContent(expression);
        while (outermostContent != null) {
            results.add(outermostContent);
            // 移除最外层的括号内容，包括括号本身，然后继续查找
            expression = expression.replaceFirst("\\(" + escapeMetaCharacters(outermostContent) + "\\)", "");
            outermostContent = findOutermostBracketsContent(expression);
        }
        return results;
    }

    // 由于正则表达式中某些字符具有特殊含义，我们需要转义这些字符
    private static String escapeMetaCharacters(String input) {
        final String[] metaCharacters = {"\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "|", "<", ">", "-", "&", "%"};

        for (String metaCharacter : metaCharacters) {
            if (input.contains(metaCharacter)) {
                input = input.replace(metaCharacter, "\\" + metaCharacter);
            }
        }
        return input;
    }

    public static void main(String[] args) {
//        String expression1 = "a=b (b=c and d=e) e=f";
//        System.out.println(findOutermostBracketsContent(expression1)); // 输出: b=c and d=e

        String expression2 = "a=b ((b=c) and (d=e) or (g=h))  and (1=2)e=f";
        List<String> allContents = findAllBracketsContent(expression2);
        for (String content : allContents) {
            System.out.println(content); // 输出: (b=c) and (d=e) or (g=h), b=c, d=e, g=h
        }
    }
}
