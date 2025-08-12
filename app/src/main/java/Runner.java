import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final char[][] grid = new char[8][];

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            for (int i = 0; i < 8; i++) {
                grid[i] = reader.line().toCharArray();
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
        var res = new Solution().solution(grid);
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
