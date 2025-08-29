import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner  {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.numbers = reader.readInts();
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
        var sol = new Solution(N, numbers);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}