package cn.syx.toolbox.gray.condition.enums;

public enum CompareEnum {

    GREATER_THAN_OR_EQUAL(">="),
    GREATER_THAN(">"),
    LESS_THAN_OR_EQUAL("<="),
    LESS_THAN("<"),
    EQUALS("="),
    IN(" in "),
    NOT_IN(" not in ");

    private final String code;

    CompareEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CompareEnum parse(String code) {
        for (CompareEnum compareEnum : CompareEnum.values()) {
            if (compareEnum.getCode().equals(code)) {
                return compareEnum;
            }
        }
        return null;
    }
}
