/**
 * Created by Taras S on 28.10.2014.
 */
public abstract class Operation implements Value {

    protected final Value[] arguments;

    public Operation(Value... operations) {
        arguments = operations;
    }

    public final int evaluate(int... params) {
        ArrayQueue variables = new ArrayQueue();
        for (int variable: params) {
            variables.add(variable);
        }
        initVariable(variables);
        return getValue();
    }

    public void initVariable(ArrayQueue variables) {
        for (Value value: arguments) {
            value.initVariable(variables);
        }
    }
}
