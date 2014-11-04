package my.parse;

import my.math.Evaluable;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileCalc {

    public static void main(String[] args) throws IOException {

//        StringReader in = new StringReader(BufferedInputFile.read("/home/taras/FileCalc.java"));
//        int c;
//
//        while ((c = in.read()) != -1) {
//            //System.out.print((char)c);
//        }

        InputStream is = new ByteArrayInputStream("100000*x*x*x*x*x*x/(x-1)\nx\n2+2\n".getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        HashMap<Integer, Evaluable> functions = new HashMap<>();
        int key = 0;
        try {
            while ((line = br.readLine()) != null) {
                functions.put(key++, new Calc(line));
            }
        } catch (ParseException e) {
            System.out.println("Parse exception " + e.getMessage());
        }

        HashMap<String, Integer> map = new HashMap<>();
        String header = "x\t\t\t";
        for (Entry<Integer, Evaluable> function: functions.entrySet()) {
            header += "f(" + function.getKey() + ")\t\t\t";
        }
        String result = header + "\n";
        for (int i = 0; i <= 10; i++) {
            if (map.get("x") == null) {
                map.put("x", i);
            } else {
                map.replace("x", i);
            }
            result += i + "\t\t\t";

            for (Entry<Integer, Evaluable> function: functions.entrySet()) {
                try {
                    result += function.getValue().evaluate(map) + "\t\t\t";
                } catch (RuntimeException e) {
                    result += e.getMessage() + "\t\t\t";
                }
            }
            result += "\n";
        }
        br.close();

        String file = "/home/taras/test.out";
        PrintWriter out = new PrintWriter(file);
        out.print(result);
        out.close();

        System.out.println("Done");
    }

}
