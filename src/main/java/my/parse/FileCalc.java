package my.parse;
import java.io.*;

public class FileCalc {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Type output file path");
            return;
        }
        InputStream input = null;
        FileOutputStream output = null;

        try {
            input = new ByteArrayInputStream("x*x\nx\n10+2-8\n".getBytes());
            CalcProcessor calcProcessor = new CalcProcessor(input);
            try {
                output = new FileOutputStream(args[0]);
                calcProcessor.write(output, 4);
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
        System.out.println("Done 1");

        // new in java 7
        // ask about project level
        // long line style
        // warning before commit
        try (
            InputStream inputNew = FileCalc.class.getResourceAsStream("/input.txt");
            FileOutputStream outputNew = new FileOutputStream(args[0])
        ) {
            CalcProcessor calcProcessor = new CalcProcessor(inputNew);
            calcProcessor.write(outputNew);
        }   // inputNew and outputNew will be closed in any case

        System.out.println("Done 2");
    }

}
