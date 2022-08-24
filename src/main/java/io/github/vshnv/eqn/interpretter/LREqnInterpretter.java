package io.github.vshnv.eqn.interpretter;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;
import io.github.vshnv.eqn.parser.grammar.RootSyntaxTreeNode;

import java.util.Map;

public class LREqnInterpretter implements EqnInterpretter {
    private final RootSyntaxTreeNode syntaxTreeNode;

    public LREqnInterpretter(final RootSyntaxTreeNode syntaxTreeNode) {
        this.syntaxTreeNode = syntaxTreeNode;
    }

    @Override
    public void execute(final Map<String, Double> variables) {

    }
}
