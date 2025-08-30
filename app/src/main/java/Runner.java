import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.BitSet;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final BitSet[] positions;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.positions = new BitSet[] { new BitSet(N * N), new BitSet(N * N) };
            for (int y = 0; y < N; ++y) {
                String line = br.readLine();
                for (int x = 0; x < N * 2; x += 2) {
                    var idx = y * N + x / 2;
                    positions[(y + x / 2) % 2].set(idx, (line.charAt(x) == '1'));
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
        int res = 0;
        for (var ps : positions) {
            res += new Solution(N, ps).solution();
        }
        bw.write(String.valueOf(res));
    }
}