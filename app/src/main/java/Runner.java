import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedReader br;
    final BufferedWriter bw;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;

        // var reader = new Reader(br);
        // try {
        //     N = Integer.parseInt(br.readLine());
        //     name = br.readLine();

        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
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
            var line = br.readLine();
            char c = line.charAt(0);
            if ('#' == c) break;
            var res = sol.solution(c, line.substring(2));
            _write(res);
        }
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}