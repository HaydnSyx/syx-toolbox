package cn.syx.toolbox.gray.condition.domain;

import cn.syx.toolbox.gray.condition.enums.TokenEnum;

public class Token {

    private final TokenEnum type;
    private final String data;
    private final int keyLength;

    public Token(TokenEnum type, String data, int keyLength) {
        this.type = type;
        this.data = data;
        this.keyLength = keyLength;
    }

    public TokenEnum getType() {
        return type;
    }

    public String getKey() {
        return this.data.substring(0, keyLength);
    }

    public String getValue() {
        return this.data.substring(keyLength - 1);
    }
}
