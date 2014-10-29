import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractTest {

    @Test
    public void testAll() {
        Variable x = new Variable("x");
        assertEquals(4,
            new Subtract(
                new Multiply(x, x),
                new Multiply(new Const(2), x),
                new Const(-1)
            ).evaluate(3)
        );
    }

    @Test
    public void testVariable() {
        assertEquals(-1, new Variable("x").evaluate(-1));
    }

    @Test(expected = IllegalStateException.class)
    public void testVariableException() {
        assertEquals(5, new Variable("x").evaluate());
    }

    @Test
    public void testSubtract() {
        assertEquals(5,
            new Subtract(
                new Const(7),
                new Const(2)
            ).evaluate()
        );
    }

    @Test
    public void testMultiply() {
        assertEquals(20,
            new Multiply(
                new Const(5),
                new Const(2),
                new Const(2)
            ).evaluate()
        );
    }

    @Test
    public void testConst() {
        assertEquals(2,
            new Const(2).evaluate()
        );
    }
}