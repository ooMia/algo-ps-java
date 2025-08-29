import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N; // 압축 방법의 개수
    final Map<Character, String> compressionMap;
    final String compressed; // 압축된 문자열
    final int l, r; // 압축 이전의 문자열 반환 범위

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(reader.line());
            this.compressionMap = new HashMap<>(N, 1.0f);
            for (int i = 0; i < N; i++) {
                String[] parts = reader.line().split(" ");
                this.compressionMap.put(parts[1].charAt(0), parts[0]);
            }
            this.compressed = reader.line();
            var _lr = reader.readInts();
            this.l = _lr[0];
            this.r = _lr[1];
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
        var sol = new Solution(compressionMap);
        var res = sol.solution(compressed, l, r);
        bw.write(String.valueOf(res));
    }
}