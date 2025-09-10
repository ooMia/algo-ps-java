import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int[] A;
    final int[] C;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            A = reader.readInts();
            C = reader.readInts();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(A, C);
        bw.write(String.valueOf(res));
    }
}