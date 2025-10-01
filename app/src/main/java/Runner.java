import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N, M; // N: 조사한 시간, M: 터널 안에 들어있는 차량의 수
    final List<Record> datas; // 특정 시간대에 입구를 통과한 차량의 수와 출구를 통과한 차량의 수

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            datas = new java.util.ArrayList<>(N);
            for (int i = 0; i < N; ++i) {
                var line = reader.readInts();
                datas.add(new Record(line[0], line[1]));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
        var res = sol.solution(M, datas);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}