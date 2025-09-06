import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int size;
    final int[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            size = Integer.parseInt(br.readLine());
            grid = new int[size][];
            for (int r = 0; r < size; ++r)
                grid[r] = reader.readInts();

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
        var sol = new Solution(size, grid);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}