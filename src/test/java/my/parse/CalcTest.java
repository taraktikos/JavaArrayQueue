package my.parse;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import java.util.HashMap;
import static org.junit.Assert.*;

public class CalcTest {

    HashMap<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
    }

    @Test
    public void testMain() throws Exception {
        map.put("x", 0);
        Calc calc = new Calc("100000*x*x*x*x*x*x/(x-1)");
        assertEquals("0", calc.evaluate(map));
        map.replace("x", 2);
        assertEquals("6400000", calc.evaluate(map));
    }

    @Test
    public void testVariable() throws Exception {
        map.put("x", 6);
        map.put("y", 2);
        Calc calc = new Calc("x+x*y");
        assertEquals("18", calc.evaluate(map));
        map.replace("y", 4);
        assertEquals("30", calc.evaluate(map));
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void testOverflowException() throws Exception {
        map.put("x", 1000);
        Calc calc = new Calc("100000*x*x*x*x*x*x/(x-1)");
        assertEquals("18", calc.evaluate(map));
    }

    @Test(expected = RuntimeException.class)
    public void testException() throws Exception {
        map.put("x", 1);
        Calc calc = new Calc("100000*x*x*x*x*x*x/(x-1)");
        assertEquals("18", calc.evaluate(map));
    }

    @Test
    public void testEvaluate() throws Exception {
        Calc calc = new Calc("2+2*2");
        assertEquals("6", calc.evaluate());

        Calc calc2 = new Calc("(2+2)*2");
        assertEquals("8", calc2.evaluate());
    }

}