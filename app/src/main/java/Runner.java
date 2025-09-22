import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int nRows, nCols;
    final boolean[][] board;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.nRows = input[0];
            this.nCols = input[1];
            this.board = new boolean[nRows][nCols];
            for (int i = 0; i < nRows; ++i) {
                String line = br.readLine();
                for (int j = 0; j < nCols; ++j) {
                    board[i][j] = line.charAt(j) == 'X';
                }
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
        var sol = new Solution();
        var res = sol.solution(nRows, nCols, board);
        bw.write(String.valueOf(res));
    }
}