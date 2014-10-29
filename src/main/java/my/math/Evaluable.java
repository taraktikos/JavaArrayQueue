package my.math;

import java.util.Map;

/**
 * Created by Taras S on 28.10.2014.
 */
public interface Evaluable {

    int evaluate();
    int evaluate(Map<String, Integer> context);

}
