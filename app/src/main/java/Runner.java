import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    int N, M; // N: number of elements, M: length of sub-sequences
    int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            sb.ensureCapacity(M * N * N);
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
        Set<String> history = new HashSet<>();
        Itertools.combinationsWithRepetition(numbers, M).forEach(chosen -> {
            String key = Arrays.toString(chosen);
            if (!history.contains(key)) {
                history.add(key);
                for (int i = 0; i < M; i++) {
                    sb.append(chosen[i]).append(' ');
                }
                sb.append('\n');
            }
        });
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
