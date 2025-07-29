import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final Solution solution = new Solution();
    final String videoLen, pos, opStart, opEnd;
    final String[] commands;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.line().split(" ");
            videoLen = input[0];
            pos = input[1];
            opStart = input[2];
            opEnd = input[3];
            commands = reader.line().split(" ");

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
            videoLen, pos, opStart, opEnd, commands
        );
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
