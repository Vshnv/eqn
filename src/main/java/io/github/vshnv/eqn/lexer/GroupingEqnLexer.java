package io.github.vshnv.eqn.lexer;

import java.util.ArrayList;
import java.util.List;

public class GroupingEqnLexer implements EqnLexer {
    @Override
    public List<Token> lex(final String input) throws InvalidTokenException {
        final List<Token> list = new ArrayList<>(input.length() / 2);
        int index = 0;
        final char[] in = input.toCharArray();
        while (index < input.length()) {
            final char cur = in[index];
            if (Character.isWhitespace(cur)) {
                index++;
            } else if (switch (cur) {
                case '+', '-', '*', '/', '^' -> true;
                default -> false;
            }) {
                list.add(new Token(TokenType.OPERATOR, Character.toString(cur), index));
                index++;
            } else if (Character.isDigit(cur)) {
                int endIndex = index + 1;
                while (endIndex < in.length && Character.isDigit(in[endIndex])) {
                    endIndex++;
                }
                if (endIndex < in.length && in[endIndex] == '.') {
                    endIndex++;
                }
                while (endIndex < in.length && Character.isDigit(in[endIndex])) {
                    endIndex++;
                }
                final String value = input.substring(index, endIndex);
                final Token tok = new Token(TokenType.NUMBER, value, index);
                index = endIndex;
                list.add(tok);
            } else if (Character.isLetter(cur)) {
                int endIndex = index;
                while (endIndex < in.length && Character.isLetter(in[endIndex])) {
                    endIndex++;
                }
                final String value = input.substring(index, endIndex);
                final Token tok = new Token(TokenType.VARIABLE, value, index);
                index = endIndex;
                list.add(tok);
            } else {
                throw new InvalidTokenException(index, input);
            }
        }
        return list;
    }
}
