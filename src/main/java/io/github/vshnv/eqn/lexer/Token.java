package io.github.vshnv.eqn.lexer;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;
import java.util.Objects;

public class Token extends SyntaxTreeNode {
    private final TokenType type;
    private final String value;
    private final int startIndex;

    public Token(final TokenType type, final String value, final int startIndex) {
        this.type = type;
        this.value = value;
        this.startIndex = startIndex;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public double evaluate(final Map<String, Double> variables) {
        return switch (type) {
            case VARIABLE -> Objects.requireNonNull(variables.get(value));
            case NUMBER -> Double.parseDouble(value);
            default -> throw new RuntimeException("Tried to evaluate unevaluatable token");
        };
    }
}
