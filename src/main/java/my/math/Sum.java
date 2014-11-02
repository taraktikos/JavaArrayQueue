package my.math;

import java.util.Map;

public class Sum extends Operation {

    public Sum(Evaluable... operations) {
        super(operations);
    }

    public int evaluate(Map<String, Integer> context) {
        int result = arguments[0].evaluate(context);
        for (int i = 1; i < arguments.length; i++) {
            result += arguments[i].evaluate(context);
        }
        return result;
    }

}
