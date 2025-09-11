import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {

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
        while (true) {
            var abc = reader.readInts();
            if (abc[0] == -1 && abc[1] == -1 && abc[2] == -1)
                break;
            var res = sol.solution(abc[0], abc[1], abc[2]);
            bw.write(String.valueOf(res));
        }
    }
}