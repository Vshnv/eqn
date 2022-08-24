package io.github.vshnv.eqn.parser;

import java.util.Map;

public abstract class SyntaxTreeNode {
    public abstract double evaluate(final Map<String, Double> variables);
}
