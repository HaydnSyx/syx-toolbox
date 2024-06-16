package io.github.haydnsyx.toolbox.base.condition.enums;

public enum TokenEnum {

    AND("and"),
    OR("or"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    ;

    private final String code;

    TokenEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
