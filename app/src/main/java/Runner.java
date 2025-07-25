import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final long N;
    final int mod = 1_000_000;
    final int[] fibo;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Long.parseLong(reader.line().trim());
            int fiboLength = (int) Math.min(N, 1_500_001L);
            fibo = new int[fiboLength + 1];
            fibo[0] = 0;
            fibo[1] = 1;
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
        if (N == 1) {
            sb.append(fibo[1]).append('\n');
            return;
        }

        for (int i = 2; i <= N; ++i) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % mod;

            if (fibo[i - 1] == fibo[0] && fibo[i] == fibo[1]) {
                int cycleLength = i - 1;
                i = (int) (N % cycleLength);
                sb.append(fibo[i]).append('\n');
                return;
            }
        }
        sb.append(fibo[(int) N]).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
