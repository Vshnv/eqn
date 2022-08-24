package io.github.vshnv.eqn.parser.lr;

public final class ReduceAction extends Action {
    private final int reductionRuleIndex;

    ReduceAction(final int reductionRuleIndex) {
        this.reductionRuleIndex = reductionRuleIndex;
    }

    public int getReductionRuleIndex() {
        return reductionRuleIndex;
    }
}
