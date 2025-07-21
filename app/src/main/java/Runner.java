import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M; // N: number of items, M: number to choose from
    final int[] numbers;
    final int[] result; // to store the current permutation
    final boolean[] visited; // to track used numbers

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            result = new int[M];
            visited = new boolean[N];
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
      generatePermutations(0);
    }

    public void generatePermutations(int depth) {
        // 베이스 케이스: 순열이 완성된 경우 (길이가 M에 도달)
        if (depth == M) {
            for (int val : result) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 재귀 단계: 사용 가능한 각 숫자에 대해 탐색
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = numbers[i];
                generatePermutations(depth + 1);
                visited[i] = false;
            }
        }
    }

}

interface IRunner {
    void run() throws IOException;

    void flush();
}
