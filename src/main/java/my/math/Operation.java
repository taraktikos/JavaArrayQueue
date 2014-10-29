package my.math;

import java.util.HashMap;

/**
 * Created by Taras S on 28.10.2014.
 */
public abstract class Operation implements Evaluable {

    protected final Evaluable[] arguments;

    public Operation(Evaluable... operations) {
        arguments = operations;
    }

    public int evaluate() {
        return this.evaluate(new HashMap());
    }

}
