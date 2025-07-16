import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.TreeSet;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K;
    final TreeSet<Integer> divisors = new TreeSet<>((a, b) -> Integer.compare(b, a));

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var input = reader.readInts();
            N = input[0];
            K = input[1];
            sb.ensureCapacity(20);

            reader.lines().forEach(line -> {
                divisors.add(Integer.parseInt(line));
            });
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
        int money = K;
        int count = 0;
        var it = divisors.iterator();
        while (it.hasNext()) {
            int divisor = it.next();
            count += money / divisor;
            money %= divisor;
        }
        sb.append(count).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}