package my.parse;

import my.math.Evaluable;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CalcProcessor {

    public static void process(InputStream input, OutputStream output)  throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String line;

        HashMap<Integer, Evaluable> functions = new HashMap<>();
        int key = 0;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                functions.put(key++, new Calc(line));
            }
        } catch (ParseException e) {
            System.out.println("Parse exception " + e.getMessage());
        }
        bufferedReader.close();

        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder header = new StringBuilder("x\t\t\t");
        for (Map.Entry<Integer, Evaluable> function: functions.entrySet()) {
            header.append("f(").append(function.getKey()).append(")\t\t\t");
        }
        header.append("\n");
        output.write(header.toString().getBytes());
        for (int i = 0; i <= 10; i++) {
            map.put("x", i);
            StringBuilder resultRow = new StringBuilder();
            resultRow.append(i).append("\t\t\t");
            for (Map.Entry<Integer, Evaluable> function: functions.entrySet()) {
                try {
                    resultRow.append(function.getValue().evaluate(map)).append("\t\t\t");
                } catch (RuntimeException e) {
                    resultRow.append(e.getMessage()).append("\t\t\t");
                }
            }
            resultRow.append("\n");
            output.write(resultRow.toString().getBytes());
        }
        output.close();
    }
}
