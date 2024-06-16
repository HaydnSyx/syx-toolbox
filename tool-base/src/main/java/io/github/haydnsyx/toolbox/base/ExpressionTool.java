package io.github.haydnsyx.toolbox.base;

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
     * 返回第一个最外层括号内的内容
     *
     * @param content 目标内容
     * @return String 最外层括号内的内容
     */
    public static String findOutermostBracketsContent(String content) {
        Stack<Integer> stack = new Stack<>();
        int leftBracketIndex = -1;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    leftBracketIndex = i; // 记录最外层左括号的位置
                }
                stack.push(i);
            } else if (content.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    // 找到了与最外层左括号对应的右括号
                    return content.substring(leftBracketIndex + 1, i);
                }
            }
        }
        return null;
    }
}
