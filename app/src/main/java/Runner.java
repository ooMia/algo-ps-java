import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Stream;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final Stream<String> inputs;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            inputs = br.lines();
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
        final String endOfScenario = "# 0", endOfCase = "0 0";
        var iter = inputs.iterator();

        int id = 1;
        while (true) {
            var line1 = iter.next();
            if (endOfCase.equals(line1))
                break;

            var s1 = line1.split(" ");
            var sol = new Solution(id++, Integer.parseInt(s1[0]), Integer.parseInt(s1[1]));
            while (true) {
                var line2 = iter.next();
                if (endOfScenario.equals(line2))
                    break;
                var s2 = line2.split(" ");
                sol.solution(s2[0].charAt(0), Integer.parseInt(s2[1]));
            }
            bw.write(sol.toString() + "\n");
        }
    }
}