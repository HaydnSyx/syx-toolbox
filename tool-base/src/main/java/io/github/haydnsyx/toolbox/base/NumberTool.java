package io.github.haydnsyx.toolbox.base;

import java.util.Objects;

/**
 * 数字类型相关工具
 *
 * @author syx
 */
public class NumberTool {

    private NumberTool() {
        throw new UnsupportedOperationException();
    }

    // 常用数字
    // short 类型
    public static final short SHORT_ZERO = 0;
    public static final short SHORT_ONE = 1;
    public static final short SHORT_TWO = 2;
    public static final short SHORT_THREE = 3;
    public static final short SHORT_FOUR = 4;
    public static final short SHORT_FIVE = 5;
    public static final short SHORT_SIX = 6;
    public static final short SHORT_SEVEN = 7;
    public static final short SHORT_EIGHT = 8;
    public static final short SHORT_NINE = 9;
    public static final short SHORT_TEN = 10;
    public static final short SHORT_ONE_HUNDRED = 100;

    // int 类型
    public static final int INT_ZERO = 0;
    public static final int INT_ONE = 1;
    public static final int INT_TWO = 2;
    public static final int INT_THREE = 3;
    public static final int INT_FOUR = 4;
    public static final int INT_FIVE = 5;
    public static final int INT_SIX = 6;
    public static final int INT_SEVEN = 7;
    public static final int INT_EIGHT = 8;
    public static final int INT_NINE = 9;
    public static final int INT_TEN = 10;
    public static final int INT_ONE_HUNDRED = 100;
    public static final int INT_ONE_THOUSAND = 1000;

    // long 类型
    public static final long LONG_ZERO = 0L;
    public static final long LONG_ONE = 1L;
    public static final long LONG_TWO = 2L;
    public static final long LONG_THREE = 3L;
    public static final long LONG_FOUR = 4L;
    public static final long LONG_FIVE = 5L;
    public static final long LONG_SIX = 6L;
    public static final long LONG_SEVEN = 7L;
    public static final long LONG_EIGHT = 8L;
    public static final long LONG_NINE = 9L;
    public static final long LONG_TEN = 10L;
    public static final long LONG_ONE_HUNDRED = 100L;
    public static final long LONG_ONE_THOUSAND = 1000L;

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(short num) {
        return num == SHORT_ZERO;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(short num) {
        return num > SHORT_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(short num) {
        return num < SHORT_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(short num) {
        return !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(short num) {
        return !isNegative(num);
    }

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(Short num) {
        return Objects.nonNull(num) && SHORT_ZERO == num;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(Short num) {
        return Objects.nonNull(num) && num > SHORT_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(Short num) {
        return Objects.nonNull(num) && num < SHORT_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(Short num) {
        return Objects.isNull(num) || !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(Short num) {
        return Objects.isNull(num) || !isNegative(num);
    }

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(int num) {
        return num == INT_ZERO;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(int num) {
        return num > INT_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(int num) {
        return num < INT_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(int num) {
        return !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(int num) {
        return !isNegative(num);
    }

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(Integer num) {
        return Objects.nonNull(num) && INT_ZERO == num;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(Integer num) {
        return Objects.nonNull(num) && num > INT_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(Integer num) {
        return Objects.nonNull(num) && num < INT_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(Integer num) {
        return Objects.isNull(num) || !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(Integer num) {
        return Objects.isNull(num) || !isNegative(num);
    }

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(long num) {
        return num == LONG_ZERO;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(long num) {
        return num > LONG_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(long num) {
        return num < LONG_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(long num) {
        return !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数与0）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(long num) {
        return !isNegative(num);
    }

    /**
     * 判断是否等于0
     *
     * @param num 判断数字
     * @return 是否为0.true：等于0；false：不等于0
     */
    public static boolean isZero(Long num) {
        return Objects.nonNull(num) && LONG_ZERO == num;
    }

    /**
     * 判断是否为正数
     *
     * @param num 判断数字
     * @return 是否为0.true：正整数；false：非正整数
     */
    public static boolean isPositive(Long num) {
        return Objects.nonNull(num) && num > LONG_ZERO;
    }

    /**
     * 判断是否为负整数
     *
     * @param num 判断数字
     * @return 是否为0.true：负整数；false：非负整数
     */
    public static boolean isNegative(Long num) {
        return Objects.nonNull(num) && num < LONG_ZERO;
    }

    /**
     * 判断是否为非正整数（负整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非正整数；false：正整数
     */
    public static boolean isNonPositive(Long num) {
        return Objects.isNull(num) || !isPositive(num);
    }

    /**
     * 判断是否为非负整数（正整数、0、空值）
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static boolean isNonNegative(Long num) {
        return Objects.isNull(num) || !isNegative(num);
    }

    /**
     * 返回一个数的绝对值
     * 修正原jdk中abs遇到最小值绝对值后还是最小值的问题
     *
     * @param num 判断数字
     * @return 是否为0.true：非负整数；false：负整数
     */
    public static int abs(int num) {
        if (num >= INT_ZERO) {
            return num;
        }

        if (num == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (~num & Integer.MAX_VALUE) + 1;
    }
}
