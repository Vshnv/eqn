package io.github.vshnv.eqn.parser.grammar;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;

public class RootSyntaxTreeNode extends SyntaxTreeNode {
    private final ExpressionSyntaxTreeNode expression;

    public RootSyntaxTreeNode(final ExpressionSyntaxTreeNode expression) {
        this.expression = expression;
    }

    public ExpressionSyntaxTreeNode getExpression() {
        return expression;
    }

    @Override
    public double evaluate(final Map<String, Double> variables) {
        return expression.evaluate(variables);
    }
}
