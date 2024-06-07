package cn.syx.toolbox.gray.condition.parse;

import cn.syx.toolbox.gray.condition.domain.Token;
import cn.syx.toolbox.gray.condition.enums.CompareEnum;
import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.domain.ConditionBuilder;
import cn.syx.toolbox.gray.condition.enums.TokenEnum;

import java.util.Iterator;
import java.util.List;

public class Parser {

    private Iterator<Token> tokens;
    private Token currentToken;

    public Condition parse(String input) {
        try {
            // 词法分析
            Lexer lexer = new Lexer();
            // 分析出语法列表
            List<Token> tokenList = lexer.tokenize(input);
            this.tokens = tokenList.iterator();
            if (this.tokens.hasNext()) {
                this.currentToken = tokens.next();
            }
            return expr().build();
        } catch (Exception e) {
            return null;
        }
    }

    private ConditionBuilder expr() {
        ConditionBuilder result = term();
        while (currentToken != null && isLogicalOperator(currentToken)) {
            consume(TokenEnum.IDENTIFIER);
            if (currentToken.getType() == TokenEnum.AND) {
                result = result.and(term());
            } else if (currentToken.getType() == TokenEnum.OR) {
                result = result.or(term());
            }
        }
        return result;
    }

    private ConditionBuilder term() {
        ConditionBuilder builder = ConditionBuilder.builder();

        if (currentToken.getType() == TokenEnum.OPEN_PAREN) {
            consume(TokenEnum.OPEN_PAREN);
            builder = expr();
            consume(TokenEnum.CLOSE_PAREN);
        } else {
            String key = currentToken.getValue();
            consume(TokenEnum.IDENTIFIER);
            String compareOp = currentToken.getValue();
            consume(TokenEnum.COMPARE_OP);
            String value = currentToken.getValue();
            consume(TokenEnum.IDENTIFIER);

            CompareEnum compareEnum = parseCompareEnum(compareOp);
            builder.and(key, compareEnum, value);
        }
        return builder;
    }

    private CompareEnum parseCompareEnum(String compareOp) {
        return switch (compareOp) {
            case "=" -> CompareEnum.EQUALS;
            case ">" -> CompareEnum.GREATER_THAN;
            case ">=" -> CompareEnum.GREATER_THAN_OR_EQUAL;
            case "<" -> CompareEnum.LESS_THAN;
            case "<=" -> CompareEnum.LESS_THAN_OR_EQUAL;
            // 处理更多的比较操作符
            default -> throw new IllegalArgumentException("Invalid compare operator: " + compareOp);
        };
    }

    private void consume(TokenEnum type) {
        if (currentToken.getType() != type) {
            throw new RuntimeException("Expected token: " + type + " but got: " + currentToken.getType());
        }
        if (tokens.hasNext()) {
            currentToken = tokens.next();
        } else {
            currentToken = null;
        }
    }

    private boolean isLogicalOperator(Token token) {
        return token.getType() == TokenEnum.AND || token.getType() == TokenEnum.OR;
    }
}
