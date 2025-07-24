import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows = 2, nCols;
    final int[][] values;
    final int[][] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            nCols = reader.readInts()[0];
            values = new int[nRows][];
            dp = new int[nRows][nCols];
            for (int i = 0; i < nRows; ++i) {
                values[i] = reader.readInts();
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
        for (int col = 0; col < nCols; ++col) {
            if (col == 0) {
                dp[0][col] = values[0][col];
                dp[1][col] = values[1][col];
            } else if (col == 1) {
                dp[0][col] = dp[1][col - 1] + values[0][col];
                dp[1][col] = dp[0][col - 1] + values[1][col];
            } else {
                {
                    // row 0
                    var case1 = dp[1][col - 1] + values[0][col];
                    var case2 = dp[1][col - 2] + values[0][col];
                    dp[0][col] = Math.max(case1, case2);
                }
                {
                    // row 1
                    var case1 = dp[0][col - 1] + values[1][col];
                    var case2 = dp[0][col - 2] + values[1][col];
                    dp[1][col] = Math.max(case1, case2);
                }
            }
        }
        var result = Math.max(dp[0][nCols - 1], dp[1][nCols - 1]);
        sb.append(result).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
