import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int T, maxT = 40;
    final int[] fibo0 = new int[maxT + 1];
    final int[] fibo1 = new int[maxT + 1];
    final List<Integer> numbers;

    int maxN = -1;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            this.T = reader.readInts()[0];
            fibo0[0] = 1;
            fibo1[1] = 1;

            numbers = reader.lines().stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            {
                var l = new ArrayList<>(numbers);
                l.sort(Integer::compareTo);
                maxN = l.get(l.size() - 1);
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
    public void run() {
        for (int i = 2; i <= maxN; i++) {
            fibo0[i] = fibo0[i - 1] + fibo0[i - 2];
            fibo1[i] = fibo1[i - 1] + fibo1[i - 2];
        }
        for (Integer n : numbers) {
            sb.append(fibo0[n]).append(' ').append(fibo1[n]).append('\n');
        }
    }

}

interface IRunner {
    void run();

    void flush();
}
