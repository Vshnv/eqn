package io.github.vshnv.eqn.parser.grammar;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;

public sealed abstract class TermSyntaxTreeNode extends SyntaxTreeNode permits TermSyntaxTreeNode.Multiplication, TermSyntaxTreeNode.Division, TermSyntaxTreeNode.Power {
    public static final class Multiplication extends TermSyntaxTreeNode {
        private final TermSyntaxTreeNode lhs;
        private final PowerSyntaxTreeNode rhs;

        public Multiplication(final TermSyntaxTreeNode lhs, final PowerSyntaxTreeNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return lhs.evaluate(variables) * rhs.evaluate(variables);
        }
    }
    public static final class Division extends TermSyntaxTreeNode {
        private final TermSyntaxTreeNode lhs;
        private final PowerSyntaxTreeNode rhs;

        public Division(final TermSyntaxTreeNode lhs, final PowerSyntaxTreeNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return lhs.evaluate(variables) / rhs.evaluate(variables);
        }
    }
    public static final class Power extends TermSyntaxTreeNode {
        private final PowerSyntaxTreeNode power;

        public Power(final PowerSyntaxTreeNode power) {
            this.power = power;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return power.evaluate(variables);
        }
    }
}
