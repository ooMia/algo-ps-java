import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: depth of a triangle
    final int[][] values; // values[depth][index]: value at a specific depth and index

    private final int[][] dp; // dp[depth][index]: maximum sum of values from the top to a specific depth and
                              // index

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            values = new int[N + 1][N + 1];
            dp = new int[N + 1][N + 1];
            for (int depth = 1; depth <= N; ++depth) {
                var line = reader.readInts();
                for (int i = 1; i <= depth; ++i) {
                    values[depth][i] = line[i - 1];
                    var left = dp[depth - 1][i - 1];
                    var right = dp[depth - 1][i];
                    dp[depth][i] = Math.max(left, right) + values[depth][i];
                }
            }
            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        var max = Arrays.stream(dp[N]).max().orElseThrow();
        sb.append(max).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
