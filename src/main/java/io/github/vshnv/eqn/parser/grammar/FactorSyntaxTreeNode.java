package io.github.vshnv.eqn.parser.grammar;

import io.github.vshnv.eqn.lexer.Token;
import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Map;
import java.util.Objects;

public sealed abstract class FactorSyntaxTreeNode extends SyntaxTreeNode permits FactorSyntaxTreeNode.Negate, FactorSyntaxTreeNode.Variable, FactorSyntaxTreeNode.Terminal {
    public static final class Negate extends FactorSyntaxTreeNode {
        private final FactorSyntaxTreeNode factor;

        public Negate(final FactorSyntaxTreeNode factor) {
            this.factor = factor;
        }

        public FactorSyntaxTreeNode getFactor() {
            return factor;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return -1 * factor.evaluate(variables);
        }
    }
    public static final class Variable extends FactorSyntaxTreeNode {
        private final String name;

        public Variable(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return Objects.requireNonNull(variables.get(name));
        }
    }
    public static final class Terminal extends FactorSyntaxTreeNode {
        private final Token terminal;

        public Terminal(final Token terminal) {
            this.terminal = terminal;
        }

        public Token getTerminal() {
            return terminal;
        }

        @Override
        public double evaluate(final Map<String, Double> variables) {
            return terminal.evaluate(variables);
        }
    }
}
