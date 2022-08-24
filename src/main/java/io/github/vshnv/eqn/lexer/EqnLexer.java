package io.github.vshnv.eqn.lexer;

import java.util.List;

public interface EqnLexer {
    List<Token> lex(final String input) throws InvalidTokenException;
}
