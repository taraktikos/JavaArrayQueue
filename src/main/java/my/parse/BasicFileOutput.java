package my.parse;

import java.io.*;

public class BasicFileOutput {

    static String file = "/home/taras/test.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("/home/taras/FileCalc.java")));
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();

        System.out.println(BufferedInputFile.read(file));
    }
}
