package my.parse;

import my.math.Evaluable;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileCalc {

    public static void main(String[] args) throws IOException {
        InputStream input = new ByteArrayInputStream("100000*x*x*x*x*x*x/(x-1)\nx\n2+2\n".getBytes());
        FileOutputStream output = new FileOutputStream("/home/taras/test.out");
        CalcProcessor.process(input, output);

        System.out.println("Done");
    }

}
