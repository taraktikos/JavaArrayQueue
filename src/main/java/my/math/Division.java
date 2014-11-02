package my.math;

import java.util.Map;

public class Division extends Operation {

    public Division(Evaluable... operations) {
        super(operations);
    }

    public int evaluate(Map<String, Integer> context) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        }

        int result = arguments[0].evaluate(context);
        for (int i = 1; i < arguments.length; i++) {
            if (arguments[i].evaluate(context) == 0) {
                throw new RuntimeException("division by zero");
            }
            result /= arguments[i].evaluate(context);
        }
        return result;
    }

}
