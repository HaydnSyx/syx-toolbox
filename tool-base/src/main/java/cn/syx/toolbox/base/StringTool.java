package cn.syx.toolbox.base;

public class StringTool {

    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String COMMA = ",";

    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";

    private StringTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断字符串是否为空(null 或 "")
     *
     * @param cs 目标字符串
     * @return boolean 是否为空。true：空；false：非空
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs 目标字符串
     * @return boolean 是否为空。true：非空；false：空
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否为空(null 或 "" 或 “    ”)
     *
     * @param cs 目标字符串
     * @return boolean 是否为空。true：空；false：非空
     */
    public static boolean isBlank(final CharSequence cs) {
        if (isEmpty(cs)) {
            return true;
        }

        return cs.chars().allMatch(Character::isWhitespace);
    }

    /**
     * 判断字符串是否为非空
     *
     * @param cs 目标字符串
     * @return boolean 是否为空。true：非空；false：空
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
