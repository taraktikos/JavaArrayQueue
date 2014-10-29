/**
 * Created by Taras S on 28.10.2014.
 */
public class Variable extends Operation {

    private final String name;
    private int value;

    public Variable(String name) {
        this.name = name;
    }

    public int getValue() {
        if (value == 0) {
            throw new IllegalStateException();
        }
        return value;
    }

    public void initVariable(ArrayQueue variables) {
        if (value == 0) {
            value = variables.remove();
        }
    }

}
