package io.github.vshnv.eqn.parser.lr;

import io.github.vshnv.eqn.lexer.Token;
import io.github.vshnv.eqn.parser.SyntaxTreeNode;
import io.github.vshnv.eqn.parser.grammar.*;

public final class LRTable {
    private final ReductionRule[] reductionRules;
    private final Action[][] actionTable;
    private final Integer[][] gotoTable;

    public LRTable(final ReductionRule[] reductionRules, final Action[][] actionTable, final Integer[][] gotoTable) {
        this.reductionRules = reductionRules;
        this.actionTable = actionTable;
        this.gotoTable = gotoTable;
    }

    public ReductionRule[] getReductionRules() {
        return reductionRules;
    }

    public Action[][] getActionTable() {
        return actionTable;
    }

    public Integer[][] getGotoTable() {
        return gotoTable;
    }

    public static int getActionStateIndex(Token token) {
        if (token == null) return 6;
        switch (token.getType()) {
            case NUMBER, VARIABLE -> {
                return 5;
            }
            case OPERATOR -> {
                switch (token.getValue()) {
                    case "+" -> {
                        return 0;
                    }
                    case "-" -> {
                        return 1;
                    }
                    case "*" -> {
                        return 2;
                    }
                    case "/" -> {
                        return 3;
                    }
                    case "^" -> {
                        return 4;
                    }
                }
            }
            case LPAREN, RPAREN -> {
                return -1;
            }
        }
        return -1;
    }

    public static int getGotoIndex(SyntaxTreeNode treeNode) {
        if (treeNode instanceof RootSyntaxTreeNode) {
            return 0;
        } else if (treeNode instanceof ExpressionSyntaxTreeNode) {
            return 1;
        } else if (treeNode instanceof TermSyntaxTreeNode) {
            return 2;
        } else if (treeNode instanceof PowerSyntaxTreeNode) {
            return 3;
        } else if (treeNode instanceof FactorSyntaxTreeNode) {
            return 4;
        }
        return -1;
    }
}
