import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int h, w;
    final String[] grid;
    final boolean[] keys = new boolean['z' - 'a' + 1];

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _hw = reader.readInts();
            this.h = _hw[0];
            this.w = _hw[1];
            this.grid = new String[h];
            for (int i = 0; i < h; ++i) {
                this.grid[i] = reader.line();
            }
            var keyLine = reader.line();
            if ("0".equals(keyLine))
                return;
            for (char c : keyLine.toCharArray()) {
                keys[c - 'a'] = true;
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
        var sol = new Solution(h, w, grid, keys);
        var res = sol.solution(keys);
        bw.write(String.valueOf(res));
    }
}