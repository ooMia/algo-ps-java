import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K; // N, K: location of A and B; 0 <= N, K <= 10^6
    final int[] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nk = reader.readInts();
            N = _nk[0];
            K = _nk[1];
            dp = new int[100_001];
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
        int res = solve(N, K);
        sb.append(res).append('\n');
    }

    private int solve(int n, int k) {
        System.err.println("solve(" + n + ", " + k + ")");
        // K가 홀수면 -1 +1 구해서 짝수로 만들고
        // K가 짝수면 N과 가장 가까운 짝수와 비교하고
        // K가 N의 거듭제곱 꼴이면 바로 반환
        if (k <= n)
            return n - k;
        if (dp[k] != 0)
            return dp[k];
        if (n == 0) {
            return dp[k] = solve(1, k) + 1;
        }

        if (k % 2 == 1) {
            int case1 = solve(n, k - 1) + 1;
            int case2 = solve(n, k + 1) + 1;
            return dp[k] = Math.min(case1, case2);
        }
        while (n < k && k % 2 == 0) {
            k /= 2;
        }
        return dp[k] = solve(n, k);
    }

}

interface IRunner {
    void run() throws IOException;

    void flush();
}
