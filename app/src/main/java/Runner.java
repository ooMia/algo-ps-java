import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;
    final int[] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            numbers = reader.readInts();
            dp = new int[N];
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
        // 10 -4 3 1 5 6 -35 12 21 -1
        // 10 6 9 10 15 16 -19 12 33 32

        int res;
        res = dp[0] = numbers[0];
        for (int i = 1; i < N; ++i) {
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            res = Math.max(res, dp[i]);
        }
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
