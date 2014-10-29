import java.util.Map;

/**
 * Created by Taras S on 28.10.2014.
 */
public class Subtract extends Operation {

    public Subtract(Evaluable... operations) {
        super(operations);
    }

    public int evaluate(Map<String, Integer> context) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        } else if (arguments.length == 1) {
            return -1 * arguments[0].evaluate(context);
        }
        int result = arguments[0].evaluate(context);
        for (int i = 1; i < arguments.length; i++) {
            result -= arguments[i].evaluate(context);
        }
        return result;
    }

}
