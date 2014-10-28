/**
 * Created by Taras S on 28.10.2014.
 */
public abstract class Operation implements Value {

    protected final Value[] arguments;

    public Operation(Value... operations) {
        arguments = operations;
    }

    public final int evaluate(int... params) {
        return getValue();
    }
}
