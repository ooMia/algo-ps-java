import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

class Runner {
    final BufferedWriter bw;

    final int N, M, K;
    final Map<String, Integer> dict;
    final String[] keys;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            var nmk = reader.readInts();
            N = nmk[0];
            M = nmk[1];
            K = nmk[2];

            dict = new java.util.HashMap<>();
            for (int i = 0; i < N; ++i) {
                var line = br.readLine().split(" ");
                dict.put(line[0], Integer.parseInt(line[1]));
            }
            keys = br.lines().toArray(String[]::new);

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
        var res = sol.solution(N, M, K, dict, keys);
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