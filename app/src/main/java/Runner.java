import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M; // N: number of items, M: number to choose from
    final int[] numbers;
    final int[] result; // to store the current permutation
    final boolean[] visited; // to track used numbers

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            result = new int[M];
            visited = new boolean[N];
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
        Itertools.combinationsWithRepetition(numbers, M)
                .forEach(l -> {
                    for (int val : l) {
                        sb.append(val).append(' ');
                    }
                    sb.append('\n');
                });
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
