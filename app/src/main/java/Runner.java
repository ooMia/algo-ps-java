import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols;
    final int[][] numbers;

    final int K;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            nRows = input[0];
            nCols = input[1];
            numbers = new int[nRows][];
            for (int r = 0; r < nRows; r++) {
                numbers[r] = reader.readInts();
            }
            K = reader.readInts()[0];

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
        var sol = new Solution(nRows, nCols, numbers);
        for (int k = 0; k < K; ++k) {
            var xy = reader.readInts();
            var res = sol.solution(xy[0], xy[1], xy[2], xy[3]);
            sb.append(res).append('\n');
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
