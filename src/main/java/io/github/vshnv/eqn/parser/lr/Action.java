package io.github.vshnv.eqn.parser.lr;

public sealed class Action permits ShiftAction, ReduceAction, AcceptAction {
    public static Action shift(int nextState) {
        return new ShiftAction(nextState);
    }

    public static Action reduce(int reductionRuleIndex) {
        return new ReduceAction(reductionRuleIndex);
    }

    public static Action accept() {
        return new AcceptAction();
    }
}