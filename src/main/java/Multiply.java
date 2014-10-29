import java.util.Map;

/**
 * Created by Taras S on 28.10.2014.
 */
public class Multiply extends Operation {

    public Multiply(Evaluable... operations) {
        super(operations);
    }

    public int evaluate(Map<String, Integer> context) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        }
        int result = 1;
        for (Evaluable argument: arguments) {
            result *= argument.evaluate(context);
        }
        return result;
    }

}
