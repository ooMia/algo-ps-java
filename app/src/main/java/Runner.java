import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] values;
    final int[][] scores;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var input = reader.readInts();
            N = input[0];
            values = reader.lines()
                    .mapToInt(Integer::parseInt)
                    .toArray();
            scores = new int[N][2];
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
        scores[0][0] = values[0];
        if (N > 1) {
            scores[1][0] = values[1];
            scores[1][1] = values[0] + values[1];
        }

        // 단순 점화식은
        // f(x) = max( arr[x] + f(x-1), arr[x] + f(x-2) )
        // 하지만 연속된 3개의 계단을 밟는 결과가 나와서는 안 됨
        // 따라서 각 위치에 대한 dp 값은 연속으로 참조한 계단의 수가 포함되어야 함

        for (int i = 0; i < N; i++) {
            forward(i, 1);
            forward(i, 2);
        }
        sb.append(Math.max(scores[N - 1][0], scores[N - 1][1])).append('\n');
    }

    void forward(int iFrom, int step) {
        if (iFrom + step >= N) {
            return;
        }

        int next = values[iFrom + step];
        int prev = 0;

        if (step == 1) {
            prev = scores[iFrom][0];
            scores[iFrom + step][1] = Math.max(scores[iFrom + step][1], prev + next);
        } else if (step == 2) {
            prev = Math.max(scores[iFrom][0], scores[iFrom][1]);
            scores[iFrom + step][0] = Math.max(scores[iFrom + step][0], prev + next);
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
