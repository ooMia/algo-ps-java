import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            numbers = reader.readInts();
            Arrays.sort(numbers);
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
        int min = Integer.MAX_VALUE;
        int[] result = new int[2];

        int iLeft = 0, iRight = N - 1;
        while (iLeft < iRight) {
            var vLeft = numbers[iLeft];
            var vRight = numbers[iRight];

            System.err.println(vLeft + " + " + vRight);
            int sum = vLeft + vRight;
            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
                result[0] = vLeft;
                result[1] = vRight;
            }
            if (sum <= 0) {
                iLeft++;
            } else {
                iRight--;
            }
        }
        sb.append(result[0]).append(' ').append(result[1]).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
