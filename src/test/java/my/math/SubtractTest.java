package my.math;

import my.math.Const;
import my.math.Multiply;
import my.math.Subtract;
import my.math.Variable;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SubtractTest {

    HashMap<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<String, Integer>();
    }

    @Test
    public void testAll() {
        map.put("x", 5);
        assertEquals(16,
            new Subtract(
                new Multiply(
                    new Variable("x"),
                    new Variable("x")
                ),
                new Multiply(
                    new Const(2),
                    new Variable("x")
                ),
                new Const(-1)
            ).evaluate(map)
        );
    }

    @Test
    public void testAll1() {
        map.put("x", 2);
        map.put("y", 3);
        assertEquals(-1,
            new Subtract(
                new Multiply(
                    new Variable("x"),
                    new Variable("x")
                ),
                new Multiply(
                    new Const(2),
                    new Variable("y")
                ),
                new Const(-1)
            ).evaluate(map)
        );
    }

    @Test
    public void testVariable() {
        map.put("x", 4);
        assertEquals(4, new Variable("x").evaluate(map));
    }

    @Test(expected = IllegalStateException.class)
    public void testVariableException() {
        assertEquals(2, new Variable("x").evaluate());
    }

    @Test
    public void testSubtract() {
        assertEquals(5,
            new Subtract(
                new Const(7),
                new Const(2)
            ).evaluate()
        );
        assertEquals(-1,
            new Subtract(
                new Const(1)
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