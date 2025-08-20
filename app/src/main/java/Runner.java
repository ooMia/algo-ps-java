import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[][] priorities;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nm = reader.readInts();
            this.N = _nm[0];
            this.M = _nm[1];
            this.priorities = new int[M][];
            for (int i = 0; i < M; ++i) {
                priorities[i] = reader.readInts();
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution(N, M, priorities);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}