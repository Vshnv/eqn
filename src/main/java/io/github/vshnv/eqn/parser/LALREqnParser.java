package io.github.vshnv.eqn.parser;

import io.github.vshnv.eqn.lexer.Token;
import io.github.vshnv.eqn.parser.grammar.ExpressionSyntaxTreeNode;
import io.github.vshnv.eqn.parser.grammar.RootSyntaxTreeNode;
import io.github.vshnv.eqn.parser.lr.*;

import java.util.*;
import java.util.function.Function;

public class LALREqnParser implements EqnParser {
    private final LRTable table;

    public LALREqnParser(final LRTable table) {
        this.table = table;
    }

    @Override
    public RootSyntaxTreeNode parse(final List<Token> tokens) {
        final Deque<SyntaxTreeNode> automataStack = new ArrayDeque<>();
        final Deque<Integer> stateStack = new ArrayDeque<>();
        final Queue<Token> inputQueue = new ArrayDeque<>(tokens);
        stateStack.push(0);
        final ReductionRule[] reductionRules = table.getReductionRules();
        final Action[][] actions = table.getActionTable();
        final Integer[][] gotos = table.getGotoTable();
        while (true) {
            final Action[] stateActions = actions[stateStack.element()];
            final Token tok = inputQueue.peek();
            final int actionStateIndex = LRTable.getActionStateIndex(tok);
            final Action action = stateActions[actionStateIndex];
            if (action instanceof ShiftAction shiftAction) {
                stateStack.push(shiftAction.getNextState());
                automataStack.push(inputQueue.poll());
            } else if (action instanceof ReduceAction reduceAction) {
                final int reductionRuleIndex = reduceAction.getReductionRuleIndex();
                final ReductionRule reductionRule = reductionRules[reductionRuleIndex];
                final SyntaxTreeNode treeNode = reductionRule.getReducer().apply(automataStack, stateStack);
                automataStack.push(treeNode);
                final int gotoIndex = LRTable.getGotoIndex(treeNode);
                final int gotoVal = gotos[stateStack.element()][gotoIndex];
                stateStack.push(gotoVal);
            } else if (action instanceof AcceptAction) {
                return new RootSyntaxTreeNode((ExpressionSyntaxTreeNode) automataStack.poll());
            } else {
                throw new RuntimeException("Invalid syntax");
            }
        }
    }

}
