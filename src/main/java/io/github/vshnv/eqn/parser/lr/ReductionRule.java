package io.github.vshnv.eqn.parser.lr;

import io.github.vshnv.eqn.parser.SyntaxTreeNode;

import java.util.Deque;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ReductionRule {
    private final BiFunction<Deque<SyntaxTreeNode>, Deque<Integer>, SyntaxTreeNode> reducer;
    public ReductionRule(BiFunction<Deque<SyntaxTreeNode>, Deque<Integer>, SyntaxTreeNode> reducer) {
        this.reducer = reducer;
    }

    public BiFunction<Deque<SyntaxTreeNode>, Deque<Integer>, SyntaxTreeNode> getReducer() {
        return reducer;
    }
}
