import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: 통화 기록의 개수
    final Map<String, Integer> usage = new HashMap<>(); // 사용자별 통화 시간

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            for (int i = 0; i < N; ++i) {
                String[] line = reader.line().split(" ");
                String h = line[0].split(":")[0];
                String m = line[0].split(":")[1];
                String user = line[1];

                int time = Integer.parseInt(h) * 60 + Integer.parseInt(m);
                usage.put(user, usage.getOrDefault(user, 0) + time);
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
        var res = new Solution().solution(usage);
        sb.append(res);
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
