import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final Solution solution = new Solution();
    final String myString;
    final String overwriteString;
    final int s;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            myString = reader.line();
            overwriteString = reader.line();
            s = Integer.parseInt(reader.line());

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
        var res = new Solution().solution(
                myString,
                overwriteString,
                s);
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
