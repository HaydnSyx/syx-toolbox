package cn.syx.toolbox.gray.condition.parse;

import cn.syx.toolbox.gray.condition.domain.Token;
import cn.syx.toolbox.gray.condition.enums.TokenEnum;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final String OPERATORS = "=><!";

    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        int keyLen = 0;
        boolean keyFlag = true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case 'A':
                case 'a':
                    if (i + 2 < input.length()
                            && (input.charAt(i + 1) == 'n' || input.charAt(i + 1) == 'N')
                            && (input.charAt(i + 2) == 'd' || input.charAt(i + 1) == 'D')) {
                        tokens.add(new Token(TokenEnum.AND, input.substring(i, i + 3), 3));
                        i = i + 2;
                        keyFlag = true;
                    } else {
                        if (keyFlag) keyLen++;
                        buffer.append(c);
                    }
                    break;
                case 'O':
                case 'o':
                    if (i + 1 < input.length()
                            && (input.charAt(i + 1) == 'r' || input.charAt(i + 1) == 'R')) {
                        tokens.add(new Token(TokenEnum.OR, input.substring(i, i + 2), 2));
                        i++;
                        keyFlag = true;
                    } else {
                        if (keyFlag) keyLen++;
                        buffer.append(c);
                    }
                    break;
                case '(':
                    tokens.add(new Token(TokenEnum.OPEN_PAREN, "(", 1));
                    keyFlag = true;
                    break;
                case ')':
                    tokens.add(new Token(TokenEnum.CLOSE_PAREN, ")", 1));
                    break;
                case '=':
                    tokens.add(new Token(TokenEnum.COMPARE_OP, String.valueOf(c), 1));
                    keyFlag = false;
                    break;
                case '>':
                case '<':
                    if (i + 1 < input.length() && input.charAt(i + 1) == '=') {
                        tokens.add(new Token(TokenEnum.COMPARE_OP, input.substring(i, i + 2), 2));
                        i++;
                    } else {
                        tokens.add(new Token(TokenEnum.COMPARE_OP, String.valueOf(c), 1));
                    }
                    keyFlag = false;
                    break;
                case ' ':
                    if (!buffer.isEmpty()) {
                        addBufferAsToken(buffer, tokens, keyLen);
                        keyLen = 0;
                    }
                    break;
                default:
                    if (keyFlag) keyLen++;
                    buffer.append(c);
            }
        }
        if (!buffer.isEmpty()) {
            addBufferAsToken(buffer, tokens, keyLen);
        }
        return tokens;
    }

    private void addBufferAsToken(StringBuilder buffer, List<Token> tokens, int len) {
        String value = buffer.toString();
        tokens.add(new Token(TokenEnum.IDENTIFIER, value, len));
        buffer.setLength(0);
    }
}
