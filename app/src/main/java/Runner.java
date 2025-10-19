import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedWriter bw;

    final int N, K;
    final long[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        var reader = new Reader(br);
        try {
            var line = reader.readInts();
            N = line[0];
            K = line[1];
            numbers = reader.readLongs();

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
        var sol = new Solution();
        var res = sol.solution(N, K, numbers);
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