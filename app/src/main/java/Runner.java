import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: number of houses
    final int[][] costs; // costs[n][rgb]: cost of painting house n with color rgb

    private final int[][] dp; // dp[n][rgb]: minimum cost to paint up to house n with color rgb

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            costs = new int[N + 1][3]; // 1-indexed
            dp = new int[N + 1][3]; // 1-indexed
            for (int n = 0; n < N; ++n) {
                var line = reader.readInts();
                for (int rgb = 0; rgb < 3; ++rgb) {
                    costs[n + 1][rgb] = line[rgb];
                    if (rgb == 0) {
                        dp[n + 1][rgb] = Math.min(dp[n][1], dp[n][2]) + costs[n + 1][rgb];
                    } else if (rgb == 1) {
                        dp[n + 1][rgb] = Math.min(dp[n][0], dp[n][2]) + costs[n + 1][rgb];
                    } else if (rgb == 2) {
                        dp[n + 1][rgb] = Math.min(dp[n][0], dp[n][1]) + costs[n + 1][rgb];
                    }
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
        int minCost = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
        sb.append(minCost).append('\n');
    }
}


interface IRunner {
    void run() throws IOException;

    void flush();
}
