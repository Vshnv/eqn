package io.github.vshnv.eqn.interpretter;

import io.github.vshnv.eqn.parser.grammar.RootSyntaxTreeNode;

import java.util.Map;

public interface NodeVisitor {
    double visit(final RootSyntaxTreeNode node, final Map<String, Double> variables);
}
