import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Stream;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final Stream<String> S;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            S = br.lines();
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
        S.forEach(s -> {
            if ("*".equals(s)) return;
            var res = sol.solution(s);
            _write(res);
        });
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}