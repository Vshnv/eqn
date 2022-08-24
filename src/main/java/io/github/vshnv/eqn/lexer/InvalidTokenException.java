package io.github.vshnv.eqn.lexer;

public class InvalidTokenException extends Exception {
    private final int index;
    private final String input;

    public InvalidTokenException(final int index, final String input) {
        super(String.format("Invalid token found at index %d", index));
        this.index = index;
        this.input = input;
    }

    public int getIndex() {
        return index;
    }

    public String getInput() {
        return input;
    }
}
