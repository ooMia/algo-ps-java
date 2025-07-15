import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K;
    final int[] numbers;
    final int[] cumuls; // sigma(0, i)

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var _nk = reader.readInts();
            N = _nk[0];
            K = _nk[1];
            sb.ensureCapacity(10);

            numbers = reader.readInts(); // size N
            cumuls = new int[N];

            cumuls[0] = numbers[0];
            for (int i = 1; i < N; ++i) {
                cumuls[i] = numbers[i] + cumuls[i - 1];
            }
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
        int max = sum(0, K - 1);
        for (int i = 1; i < N; ++i) {
            int j = i + K - 1;
            if (j >= N) {
                break;
            }
            max = Math.max(max, sum(i, j));
        }
        sb.append(max).append('\n');
    }

    private int sum(int iFrom, int iTo) {
        if (iFrom <= 0) {
            return cumuls[iTo];
        }
        return cumuls[iTo] - cumuls[iFrom - 1];
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}