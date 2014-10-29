/**
 * Created by Taras S on 28.10.2014.
 */
public class Const extends Operation {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void initVariable(ArrayQueue variables) {}
}
