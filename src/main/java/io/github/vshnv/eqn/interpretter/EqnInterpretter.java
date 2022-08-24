package io.github.vshnv.eqn.interpretter;

import java.util.Map;

public interface EqnInterpretter {
    void execute(final Map<String, Double> variables);
}
