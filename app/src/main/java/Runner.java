import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedWriter bw;

    final int N;
    final int[][] points;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            N = Integer.parseInt(br.readLine());
            points = new int[N][];
            for (int i = 0; i < N; ++i) {
                points[i] = reader.readInts();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
        var sol = new Solution(N, points);
        var res = sol.solution();
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}