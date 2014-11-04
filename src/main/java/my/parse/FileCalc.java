package my.parse;

import java.io.*;

public class FileCalc {

    public static void main(String[] args) throws IOException {

        StringReader in = new StringReader(BufferedInputFile.read("/home/taras/FileCalc.java"));
        int c;

        while ((c = in.read()) != -1) {
            //System.out.print((char)c);
        }

        InputStream is = new ByteArrayInputStream("a\nb\nc\n".getBytes());

        // read it with BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

}
