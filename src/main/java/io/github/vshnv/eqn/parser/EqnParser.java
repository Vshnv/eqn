package io.github.vshnv.eqn.parser;

import io.github.vshnv.eqn.lexer.Token;
import io.github.vshnv.eqn.parser.grammar.RootSyntaxTreeNode;

import java.util.List;

public interface EqnParser {
    RootSyntaxTreeNode parse(List<Token> tokens);
}
