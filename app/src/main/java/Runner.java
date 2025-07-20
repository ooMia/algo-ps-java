import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nX, nY, K; // K: number of elements
    final boolean[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _xyk = reader.readInts();
            nX = _xyk[0];
            nY = _xyk[1];
            K = _xyk[2];
            grid = new boolean[nY][nX];
            sb.ensureCapacity(20);

            for (int i = 0; i < K; ++i) {
                var xy = reader.readInts();
                grid[xy[1]][xy[0]] = true;
            }
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
        int count = 0;
        for (int y = 0; y < nY; ++y) {
            for (int x = 0; x < nX; ++x) {
                if (grid[y][x]) {
                    search(y, x);
                    count++;
                }
            }
        }
        sb.append(count).append('\n');
    }

    private void search(int y, int x) {
        if (y < 0 || y >= nY || x < 0 || x >= nX || !grid[y][x]) {
            return;
        }

        grid[y][x] = false; // Mark as visited

        search(y - 1, x); // Up
        search(y + 1, x); // Down
        search(y, x - 1); // Left
        search(y, x + 1); // Right
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
