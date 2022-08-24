package io.github.vshnv.eqn.interpretter;

import io.github.vshnv.eqn.parser.grammar.RootSyntaxTreeNode;

import java.util.Map;

public class RootSyntaxTreeNodeVisitor implements NodeVisitor {
    @Override
    public double visit(final RootSyntaxTreeNode node, final Map<String, Double> variables) {
        return 0;
    }
}
