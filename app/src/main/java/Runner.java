import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;
    final int[] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            numbers = reader.readInts();
            dp = new int[N];
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
        // 11 10 90 20 2 30 4 6 8 50
        // 11
        // 10 [0]
        // 10 90 [1]
        // 10 20 [1]
        // 2 20 [0]
        // 2 20 30 [2]
        // 2 4 30 [1]
        // 2 4 6 [2]
        // 2 4 6 8 [3]
        // 2 4 6 8 50 [4]

        // 1 3 5 7 9 11 13 15 17 19 & 6 [2]
        // [0,9]_4 -> 9>6
        // [0,4-1]_3 -> 7>6
        // [0,3-1]_1 -> 3<6
        // [2,2]_
        // mid 2
        dp[0] = numbers[0];
        int res = 1;

        for (int i = 1; i < N; ++i) {
            System.err.println(Arrays.toString(dp));

            // number가 마지막 dp보다 크면 추가
            var num = numbers[i];
            if (dp[res - 1] < num) {
                dp[res++] = num;
                continue;
            }

            // number가 첫 번째 dp보다 작으면 교체
            if (num < dp[0]) {
                dp[0] = num;
                continue;
            }

            // 그 외에는 이진 탐색으로 교체 위치 찾기
            // 교체할 dp는 항상 num보다 크거나 같아야 함
            int left = 0, right = res; // [left, right)
            while (left < right) {
                int mid = (left + right) / 2;
                if (dp[mid] < num) {
                    left = mid + 1; // 오른쪽 탐색
                } else if (dp[mid] > num) {
                    right = mid; // 왼쪽 탐색
                } else {
                    left = mid;
                    break;
                }
            }
            dp[left] = num; // 교체
        }
        System.err.println(Arrays.toString(dp));
        sb.append(res).append('\n');
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
