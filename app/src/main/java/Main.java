import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            // int T = Integer.parseInt(br.readLine().trim());
            // for (int i = 0; i < T; ++i)
            {
                // System.err.println("\ncase " + (i + 1));
                // long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                // long end = System.currentTimeMillis();
                // System.err.println("took " + (end - start) + " ms");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
