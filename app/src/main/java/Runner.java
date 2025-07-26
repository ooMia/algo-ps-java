import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, X;
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            X = reader.readInts()[0];
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
        int count = 0;
        int iLeft = 0, iRight = N - 1;
        while (iLeft < iRight) {
            int sum = numbers[iLeft] + numbers[iRight];
            if (sum == X) {
                count++;
                iLeft++;
                iRight--;
            } else if (sum < X) {
                iLeft++;
            } else {
                iRight--;
            }
        }
        sb.append(count).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
