import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final Map<String, Integer> users;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.users = new HashMap<>(N, 1);
            for (int n = 0; n < N; ++n) {
                var name = br.readLine();
                users.put(name, users.getOrDefault(name, 0) + 1);
            }
            for (int n = 0; n < N - 1; ++n) {
                var name = br.readLine();
                var v = users.get(name);
                if (v == 1) {
                    users.remove(name);
                } else {
                    users.put(name, v - 1);
                }
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
        var res = new Solution(N, users).solution();
        bw.write(String.valueOf(res));
    }
}