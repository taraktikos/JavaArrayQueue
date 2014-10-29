package my.parse;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {

    @Test
    public void testEvaluate() throws Exception {
        Calc calc = new Calc("2+3");
        calc.parse();
        assertEquals("5", calc.evaluate());
    }

    @Test
    public void testParse() throws Exception {
        Calc calc = new Calc("2+3");
        assertEquals("23+", calc.parse());
    }
}