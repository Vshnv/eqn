package io.github.vshnv.eqn.parser.grammar;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;

public sealed abstract class PowerSyntaxTreeNode extends SyntaxTreeNode permits PowerSyntaxTreeNode.Exponent, PowerSyntaxTreeNode.Factor {
    public static final class Exponent extends PowerSyntaxTreeNode {
        private final PowerSyntaxTreeNode lhs;
        private final FactorSyntaxTreeNode rhs;

        public Exponent(final PowerSyntaxTreeNode lhs, final FactorSyntaxTreeNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return Math.pow(lhs.evaluate(variables), rhs.evaluate(variables));
        }
    }
    public static final class Factor extends PowerSyntaxTreeNode {
        private final FactorSyntaxTreeNode factor;

        public Factor(final FactorSyntaxTreeNode factor) {
            this.factor = factor;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return factor.evaluate(variables);
        }
    }
}
