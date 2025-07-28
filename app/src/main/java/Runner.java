import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: length of string
    final String[] tokens;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            tokens = new String[2];
            tokens[0] = reader.line();
            tokens[1] = reader.line();
            N = tokens[0].length();
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
        int n = tokens[0].length();
        int m = tokens[1].length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (tokens[0].charAt(i - 1) == tokens[1].charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        var res = dp[n][m];
        sb.append(res).append('\n');
        if (res != 0) {
            int y = n, x = m;
            StringBuilder lcs = new StringBuilder();
            while (y > 0 && x > 0) {
                if (tokens[0].charAt(y - 1) == tokens[1].charAt(x - 1)) {
                    lcs.append(tokens[0].charAt(y - 1));
                    --y;
                    --x;
                } else if (dp[y - 1][x] >= dp[y][x - 1]) {
                    --y;
                } else {
                    --x;
                }
            }
            lcs.reverse();
            sb.append(lcs).append('\n');
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
