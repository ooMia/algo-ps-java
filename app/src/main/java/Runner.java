import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;
    final int M;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            numbers = reader.readInts();
            this.M = reader.readInts()[0];

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
        var sol = new Solution(N, numbers);
        for (int m = 0; m < M; m++) {
            var input = reader.readInts();
            var res = sol.solution(input[0] - 1, input[1] - 1);
            sb.append(res).append('\n');
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
