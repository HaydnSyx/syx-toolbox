package cn.syx.toolbox.base;

public class StringTool {

    private StringTool() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        if (isEmpty(cs)) {
            return true;
        }

        return cs.chars().allMatch(Character::isWhitespace);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
