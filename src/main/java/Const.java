import java.util.Map;

/**
 * Created by Taras S on 28.10.2014.
 */
public class Const extends Operation {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    public int evaluate(Map<String, Integer> context) {
        return value;
    }
}
