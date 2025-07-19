import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final Map<String, String> storage;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nm = reader.readInts();
            N = _nm[0];
            M = _nm[1];
            storage = new HashMap<>(N);
            for (int i = 0; i < N; ++i) {
                var input = reader.line().split(" ");
                storage.put(input[0], input[1]);
            }
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
        for (int i = 0; i < M; ++i) {
            var key = reader.line();
            var value = storage.get(key);
            sb.append(value).append('\n');
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
