package my.parse;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class CalcProcessorTest {

    @Test
    public void testWrite() throws Exception {
        InputStream input = new ByteArrayInputStream("x*x\nx\n2+2\n".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        CalcProcessor calcProcessor = new CalcProcessor(input);
        input.close();
        calcProcessor.write(output, 2);
        output.close();

        StringBuilder result = new StringBuilder("x\t\t\tf(0)\t\t\tf(1)\t\t\tf(2)\t\t\t\n");
        result.append("0\t\t\t0\t\t\t0\t\t\t4\t\t\t\n")
                .append("1\t\t\t1\t\t\t1\t\t\t4\t\t\t\n");

        assertEquals(result.toString(), output.toString());
    }
}