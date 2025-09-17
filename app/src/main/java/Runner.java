import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final Map<Integer, Integer> closet = new HashMap<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
                String[] parts = br.readLine().split(" ");
                int type = parts[1].hashCode();
                closet.put(type, closet.getOrDefault(type, 0) + 1);
            }

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
        var sol = new Solution(closet);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}