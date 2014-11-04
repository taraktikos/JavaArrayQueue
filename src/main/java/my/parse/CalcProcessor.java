package my.parse;

import my.math.Evaluable;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CalcProcessor {

    private HashMap<Integer, Evaluable> parsedFunctions;

    public CalcProcessor(InputStream input) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String line;

        parsedFunctions = new HashMap<>();
        int key = 0;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                parsedFunctions.put(key++, new Calc(line));
            }
        } catch (ParseException e) {
            System.out.println("Parse exception " + e.getMessage());
        }
    }

    public void write(OutputStream output) throws IOException {
        write(output, 10);
    }

    public void write(OutputStream output, int maxCount) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder header = new StringBuilder("x\t\t\t");
        for (Map.Entry<Integer, Evaluable> function: parsedFunctions.entrySet()) {
            header.append("f(").append(function.getKey()).append(")\t\t\t");
        }
        header.append("\n");
        output.write(header.toString().getBytes());
        for (int i = 0; i <= maxCount - 1; i++) {
            map.put("x", i);
            StringBuilder resultRow = new StringBuilder();
            resultRow.append(i).append("\t\t\t");
            for (Map.Entry<Integer, Evaluable> function: parsedFunctions.entrySet()) {
                try {
                    resultRow.append(function.getValue().evaluate(map)).append("\t\t\t");
                } catch (RuntimeException e) {
                    resultRow.append(e.getMessage()).append("\t\t\t");
                }
            }
            resultRow.append("\n");
            output.write(resultRow.toString().getBytes());
        }
    }
}
