import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[] cumuls;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var input = reader.readInts();
            N = input[0];
            M = input[1];
            sb.ensureCapacity(M * 2);
            
            cumuls = new int[N];
            int[] numbers = reader.readInts();
            cumuls[0] = numbers[0];
            for (int i = 1; i < N; ++i) {
                cumuls[i] = cumuls[i - 1] + numbers[i];
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
        for (int i = 0; i < M; ++i) {
            var inputs = reader.readInts();
            int iFrom = inputs[0] - 1, iTo = inputs[1] - 1;
            if (iFrom == 0) {
                sb.append(cumuls[iTo]).append('\n');
            } else {
                sb.append(cumuls[iTo] - cumuls[iFrom - 1]).append('\n');
            }
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
