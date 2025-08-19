import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    KeyBoard[] keyBoards;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var M = reader.readInts()[1];
            this.keyBoards = new KeyBoard[M];
            for (int i = 0; i < M; ++i) {
                var input = reader.line().split(" ");
                var id = Integer.parseInt(input[0]);
                var delay = Integer.parseInt(input[1]);
                var key = input[2].charAt(0);
                keyBoards[i] = new KeyBoard(id, delay, key);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(keyBoards);
        sb.append(res).append('\n');
    }
}