package my.parse;

import java.text.ParseException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String expression = "100000*x*x*x*x*x*x/(x-1)";
        if (args.length == 0) {
            System.out.println("Results for " + expression);
        } else {
            expression = args[0];
        }
        try {
            Calc calc = new Calc(expression);
            HashMap<String, Integer> map = new HashMap<>();
            System.out.println("x\tf(x)");
            for (int i = 0; i <= 10; i++) {
                if (map.get("x") == null) {
                    map.put("x", i);
                } else {
                    map.replace("x", i);
                }
                try {
                    String result = Integer.toString(calc.evaluate(map));
                    System.out.println(i + "\t" + result);
                } catch (RuntimeException e) {
                    System.out.println(i + "\t" + e.getMessage());
                }
            }
        } catch (ParseException e) {
            System.out.println("Parse exception " + e.getMessage());
        }
    }

}
