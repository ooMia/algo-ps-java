import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int A, B, C, M; // A: 피로도 증가량, B: 처리량, C: 피로도 감소량, M: 최대 피로도

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.A = input[0];
            this.B = input[1];
            this.C = input[2];
            this.M = input[3];
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
        var res = new Solution().solution(A, B, C, M);
        bw.write(String.valueOf(res));
    }
}