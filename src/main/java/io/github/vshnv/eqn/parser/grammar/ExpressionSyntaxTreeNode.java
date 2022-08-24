package io.github.vshnv.eqn.parser.grammar;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;

public sealed abstract class ExpressionSyntaxTreeNode extends SyntaxTreeNode permits ExpressionSyntaxTreeNode.Addition, ExpressionSyntaxTreeNode.Subtraction, ExpressionSyntaxTreeNode.Term {
    public static final class Addition extends ExpressionSyntaxTreeNode {
        private final ExpressionSyntaxTreeNode lhs;
        private final TermSyntaxTreeNode rhs;

        public Addition(final ExpressionSyntaxTreeNode lhs, final TermSyntaxTreeNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        public ExpressionSyntaxTreeNode getLHS() {
            return lhs;
        }

        public TermSyntaxTreeNode getRHS() {
            return rhs;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return lhs.evaluate(variables) + rhs.evaluate(variables);
        }
    }
    public static final class Subtraction extends ExpressionSyntaxTreeNode {
        private final ExpressionSyntaxTreeNode lhs;
        private final TermSyntaxTreeNode rhs;

        public Subtraction(final ExpressionSyntaxTreeNode lhs, final TermSyntaxTreeNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        public ExpressionSyntaxTreeNode getLHS() {
            return lhs;
        }

        public TermSyntaxTreeNode getRHS() {
            return rhs;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return lhs.evaluate(variables) - rhs.evaluate(variables);
        }
    }
    public static final class Term extends ExpressionSyntaxTreeNode {
        private final TermSyntaxTreeNode term;

        public Term(final TermSyntaxTreeNode term) {
            this.term = term;
        }

        public TermSyntaxTreeNode getTerm() {
            return term;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return term.evaluate(variables);
        }
    }
}