import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedWriter bw;

    final int N;
    final int[][] relations;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            var line = reader.readInts();
            N = line[0];
            var m = line[1];
            relations = new int[m][];
            for (int i = 0; i < relations.length; ++i) {
                relations[i] = reader.readInts();
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
        var sol = new Solution(N,relations);
        var res = sol.solution( );
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