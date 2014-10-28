import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractTest {

    @Test
    public void testSubtract() {
        assertEquals(1,
            new Subtract(
                new Multiply(
                    new Const(5),
                    new Const(2)
                ),
                new Const(7),
                new Const(2)
            ).evaluate()
        );
    }
}