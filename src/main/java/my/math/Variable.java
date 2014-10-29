package my.math;

import java.util.Map;

/**
 * Created by Taras S on 28.10.2014.
 */
public class Variable extends Operation {

    private final String name;
    private int value;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(Map<String, Integer> context) {
        if (context.get(name) == null) {
            throw new IllegalStateException();
        }
        return context.get(name);
    }

}
