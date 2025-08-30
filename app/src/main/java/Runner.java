import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.BitSet;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final BitSet positions;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.positions = new BitSet(N * N);
            for (int y = 0; y < N; ++y) {
                String line = br.readLine();
                for (int x = 0; x < N * 2; x += 2)
                    positions.set(y * N + x / 2, (line.charAt(x) == '1'));
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
        var sol = new Solution(N, positions);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}