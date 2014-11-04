package my.parse;

import java.io.*;

public class FileCalc {

    public static void main(String[] args) throws IOException {

        StringReader in = new StringReader(BufferedInputFile.read("/home/taras/FileCalc.java"));
        int c;

        while ((c = in.read()) != -1) {
            System.out.print((char)c);
        }
    }

}
