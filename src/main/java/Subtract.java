/**
 * Created by Taras S on 28.10.2014.
 */
public class Subtract extends Operation {

    public Subtract(Value... operations) {
        super(operations);
    }

    public int getValue() {
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        }
        int result = arguments[0].getValue();
        for (int i = 1; i < arguments.length; i++) {
            result -= arguments[i].getValue();
        }
        return result;
    }

}
