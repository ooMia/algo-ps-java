import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final long[] L1;
    final long[] L2;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.L1 = reader.readLongs();
            this.L2 = reader.readLongs();
        } catch (IOException e) {
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
        var res = sol.solution(L1, L2);
        bw.write(String.valueOf(res));
    }
}