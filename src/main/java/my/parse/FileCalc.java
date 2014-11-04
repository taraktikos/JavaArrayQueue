package my.parse;
import java.io.*;

public class FileCalc {

    public static void main(String[] args) throws IOException {
        InputStream input = new ByteArrayInputStream("x*x\nx\n10+2-8\n".getBytes());
        FileOutputStream output = new FileOutputStream(FileCalc.class.getResource("/output.txt").getPath());

        try {
            CalcProcessor calcProcessor = new CalcProcessor(input);
            try {
                calcProcessor.write(output, 4);
            } finally {
                output.close();
            }
        } finally {
            input.close();
        }
        System.out.println("Done 1");

        // new in java 7
        // ask about project level
        // long line style
        // warning before commit
        try (
            InputStream inputNew = FileCalc.class.getResourceAsStream("/input.txt");
            FileOutputStream outputNew = new FileOutputStream(FileCalc.class.getResource("/output2.txt").getPath())
        ) {
            CalcProcessor calcProcessor = new CalcProcessor(inputNew);
            calcProcessor.write(outputNew);
        }   // inputNew and outputNew will be closed in any case

        System.out.println("Done 2");
    }

}
