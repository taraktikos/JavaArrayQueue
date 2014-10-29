/**
 * Created by Taras S on 28.10.2014.
 */
public class Multiply extends Operation {

    public Multiply(Value... operations) {
        super(operations);
    }

    public int getValue() {
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        }
        int result = 1;
        for (Value argument: arguments) {
            result *= argument.getValue();
        }
        return result;
    }

}
