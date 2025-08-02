import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            reader.skipLine();
            numbers = reader.readInts();
            Arrays.sort(numbers);

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
        var res = new Solution().solution(numbers);
        Arrays.sort(res);
        sb.append(res[0]).append(' ')
                .append(res[1]).append(' ')
                .append(res[2]).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
