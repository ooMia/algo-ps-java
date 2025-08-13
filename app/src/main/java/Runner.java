import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols;
    int rDest, cDest;
    final char[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var rc = reader.readInts();
            this.nRows = rc[0];
            this.nCols = rc[1];
            this.grid = new char[nRows][nCols];
            for (int row = 0; row < nRows; ++row) {
                var s = reader.line();
                for (int i = 0, col = 0; i < s.length(); i += 2) {
                    var c = s.charAt(i);
                    if (c == '2') {
                        this.rDest = row;
                        this.cDest = col;
                    }
                    this.grid[row][col++] = c;
                }
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
        var sol = new Solution(nRows, nCols, grid, rDest, cDest);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
