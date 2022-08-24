package io.github.vshnv.eqn.parser.lr;

public final class ShiftAction extends Action {
    private final int nextState;

    ShiftAction(final int nextState) {
        this.nextState = nextState;
    }

    public int getNextState() {
        return nextState;
    }
}
