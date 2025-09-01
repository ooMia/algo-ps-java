import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final List<int[]> monitors;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.monitors = new ArrayList<>(N);
            for (int n = 0, id = 1; n < N; ++n, ++id) {
                var wh = reader.readInts();
                this.monitors.add(new int[] { wh[0], wh[1], id });
            }
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
        var res = new Solution(N, monitors).solution();
        bw.write(String.valueOf(res));
    }
}