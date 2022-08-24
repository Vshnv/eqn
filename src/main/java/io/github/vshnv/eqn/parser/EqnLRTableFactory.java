package io.github.vshnv.eqn.parser;

import io.github.vshnv.eqn.lexer.Token;
import io.github.vshnv.eqn.parser.grammar.*;
import io.github.vshnv.eqn.parser.lr.Action;
import io.github.vshnv.eqn.parser.lr.LRTable;
import io.github.vshnv.eqn.parser.lr.ReductionRule;

import static io.github.vshnv.eqn.parser.lr.Action.*;

public final class EqnLRTableFactory {
    private EqnLRTableFactory() {}

    public static LRTable createEqnLRTable() {
        final Action[][] actionsTable = new Action[18][7];
        final Integer[][] gotoTable = new Integer[18][5];

        actionsTable[0] = new Action[]{
                null, shift(5), null, null, null, shift(6), null
        };
        actionsTable[1] = new Action[]{
                shift(7), shift(8), null, null, null, null, accept()
        };
        actionsTable[2] = new Action[]{
                reduce(3), reduce(3), shift(9), shift(10), null, null, reduce(3)
        };
        actionsTable[3] = new Action[]{
                reduce(6), reduce(6), reduce(6), reduce(6), shift(11), null, reduce(6)
        };
        actionsTable[4] = new Action[]{
                reduce(8), reduce(8), reduce(8), reduce(8), reduce(8), null, reduce(8)
        };
        actionsTable[5] = new Action[]{
                null, null, null, null, null, shift(6), null
        };
        actionsTable[6] = new Action[]{
                reduce(10), reduce(10), reduce(10), reduce(10), reduce(10), null, reduce(10)
        };
        actionsTable[7] = new Action[]{
                null, shift(5), null, null, null, shift(6), null
        };
        actionsTable[8] = new Action[]{
                null, shift(5), null, null, null, shift(6), null
        };
        actionsTable[9] = new Action[]{
                null, shift(5), null, null, null, shift(6), null
        };
        actionsTable[10] = new Action[]{
                null, shift(5), null, null, null, shift(6), null
        };
        actionsTable[11] = new Action[]{
                null, null, null, null, null, shift(6), null
        };
        actionsTable[12] = new Action[]{
                reduce(9), reduce(9), reduce(9), reduce(9), reduce(9), null, reduce(9)
        };
        actionsTable[13] = new Action[]{
                reduce(1), reduce(1), shift(9), shift(10), null, null, reduce(1)
        };
        actionsTable[14] = new Action[]{
                reduce(2), reduce(2), shift(9), shift(10), null, null, reduce(2)
        };
        actionsTable[15] = new Action[]{
                reduce(4), reduce(4), reduce(4), reduce(4), shift(11), null, reduce(4)
        };
        actionsTable[16] = new Action[]{
                reduce(5), reduce(5), reduce(5), reduce(5), shift(11), null, reduce(5)
        };
        actionsTable[17] = new Action[]{
                reduce(7), reduce(7), reduce(7), reduce(7), reduce(7), null, reduce(7)
        };

        gotoTable[0] = new Integer[] {
                null, 1, 2, 3, 4
        };
        gotoTable[5] = new Integer[] {
                null, null, null, null, 12
        };
        gotoTable[7] = new Integer[] {
                null, null, 13, 3, 4
        };
        gotoTable[8] = new Integer[] {
                null, null, 14, 3, 4
        };
        gotoTable[9] = new Integer[] {
                null, null, null, 15, 4
        };
        gotoTable[10] = new Integer[] {
                null, null, null, 16, 4
        };
        gotoTable[11] = new Integer[] {
                null, null, null, null, 17
        };
        final Integer[] subArrays = new Integer[5];
        for (int i = 0; i < gotoTable.length; i++) {
            if (gotoTable[i] == null) {
                gotoTable[i] = subArrays;
            }
        }

        final ReductionRule[] reductionRules = new ReductionRule[11];
        reductionRules[0] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode node = stack.pop();
            stateStack.pop();
            if (node instanceof ExpressionSyntaxTreeNode) {
                return new RootSyntaxTreeNode((ExpressionSyntaxTreeNode) node);
            }
            throw new RuntimeException("Invalid syntax");
        });
        reductionRules[1] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            final SyntaxTreeNode lhs = stack.pop();
            stateStack.pop();
            stateStack.pop();
            stateStack.pop();
            if ( lhs instanceof ExpressionSyntaxTreeNode &&
                (op instanceof Token && "+".equals(((Token) op).getValue())) &&
                 rhs instanceof TermSyntaxTreeNode) {
                return new ExpressionSyntaxTreeNode.Addition((ExpressionSyntaxTreeNode) lhs, (TermSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[2] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            final SyntaxTreeNode lhs = stack.pop();
            stateStack.pop();
            stateStack.pop();
            stateStack.pop();
            if ( lhs instanceof ExpressionSyntaxTreeNode &&
                    (op instanceof Token && "-".equals(((Token) op).getValue())) &&
                    rhs instanceof TermSyntaxTreeNode) {
                return new ExpressionSyntaxTreeNode.Subtraction((ExpressionSyntaxTreeNode) lhs, (TermSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[3] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode term = stack.pop();
            stateStack.pop();
            if (term instanceof TermSyntaxTreeNode) {
                return new ExpressionSyntaxTreeNode.Term((TermSyntaxTreeNode) term);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[4] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            final SyntaxTreeNode lhs = stack.pop();
            stateStack.pop();
            stateStack.pop();
            stateStack.pop();
            if ( lhs instanceof TermSyntaxTreeNode &&
                    (op instanceof Token && "*".equals(((Token) op).getValue())) &&
                    rhs instanceof PowerSyntaxTreeNode) {
                return new TermSyntaxTreeNode.Multiplication((TermSyntaxTreeNode) lhs, (PowerSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });
        reductionRules[5] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            final SyntaxTreeNode lhs = stack.pop();
            stateStack.pop();
            stateStack.pop();
            stateStack.pop();
            if ( lhs instanceof TermSyntaxTreeNode &&
                    (op instanceof Token && "/".equals(((Token) op).getValue())) &&
                    rhs instanceof PowerSyntaxTreeNode) {
                return new TermSyntaxTreeNode.Division((TermSyntaxTreeNode) lhs, (PowerSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[6] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode power = stack.pop();
            stateStack.pop();
            if (power instanceof PowerSyntaxTreeNode) {
                return new TermSyntaxTreeNode.Power((PowerSyntaxTreeNode) power);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[7] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            final SyntaxTreeNode lhs = stack.pop();
            stateStack.pop();
            stateStack.pop();
            stateStack.pop();
            if ( lhs instanceof PowerSyntaxTreeNode &&
                    (op instanceof Token && "^".equals(((Token) op).getValue())) &&
                    rhs instanceof FactorSyntaxTreeNode) {
                return new PowerSyntaxTreeNode.Exponent((PowerSyntaxTreeNode) lhs, (FactorSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[8] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode factor = stack.pop();
            stateStack.pop();
            if (factor instanceof FactorSyntaxTreeNode) {
                return new PowerSyntaxTreeNode.Factor((FactorSyntaxTreeNode) factor);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[9] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode rhs = stack.pop();
            final SyntaxTreeNode op = stack.pop();
            stateStack.pop();
            stateStack.pop();
            if ((op instanceof Token && "^".equals(((Token) op).getValue())) &&
                    rhs instanceof FactorSyntaxTreeNode) {
                return new FactorSyntaxTreeNode.Negate((FactorSyntaxTreeNode) rhs);
            }
            throw new RuntimeException("Invalid syntax");
        });

        reductionRules[10] = new ReductionRule((stack, stateStack) -> {
            final SyntaxTreeNode literal = stack.pop();
            stateStack.pop();
            if (literal instanceof Token) {
                return new FactorSyntaxTreeNode.Terminal((Token) literal);
            }
            throw new RuntimeException("Invalid syntax");
        });

        return new LRTable(reductionRules, actionsTable, gotoTable);
    }
}
