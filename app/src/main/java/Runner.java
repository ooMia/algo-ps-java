import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final char[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            this.grid = new char[N][N];
            for (int r = 0; r < N; ++r) {
                var s = reader.line();
                for (int i = 0, c = 0; i < s.length(); i += 2)
                    this.grid[r][c++] = s.charAt(i);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        var sol = new Solution(grid);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
