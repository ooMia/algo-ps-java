import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedWriter bw;

    final int T;
    final int[] ns;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        // var reader = new Reader(br);
        try {
            T = Integer.parseInt(br.readLine());
            ns = br.lines().mapToInt(Integer::parseInt).toArray();

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
        var res = sol.solution(ns);
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