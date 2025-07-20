import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, C; // N: 아이템 개수, C: 최대 소지 무게
    final int[] weights;
    final long[] cumuls;
    final List<Map<Integer, Integer>> dp; // dp[i][w]

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _xyk = reader.readInts();
            N = _xyk[0];
            C = _xyk[1];
            weights = reader.readInts();
            cumuls = new long[N];
            cumuls[0] = weights[0];
            dp = new java.util.ArrayList<>(N);
            dp.add(new java.util.HashMap<>());
            for (int i = 1; i < N; ++i) {
                dp.add(new java.util.HashMap<>());
                cumuls[i] = cumuls[i - 1] + weights[i];
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
        // 무게 C에 대해 i-index 이후의 아이템을 선택하는 경우의 수 + 아무것도 선택하지 않는 경우
        var res = pack(C, 0, N) + 1;
        sb.append(res).append('\n');

        // for (int i = 0; i < N; ++i) {
        // System.err.println(Arrays.toString(dp.get(i).entrySet().toArray()));
        // }
    }

    private int pack(int wLimit, int iFrom, int iTo) {
        // 선택 불가능
        if (iTo <= iFrom || wLimit <= 0) {
            return 0;
        }

        var dp_i = dp.get(iFrom);
        var w = weights[iFrom];

        // 남은 아이템을 모두 선택할 수 있는 경우
        var cumul = (iFrom > 0) ? cumuls[iTo - 1] - cumuls[iFrom - 1] : cumuls[iTo - 1];
        if (cumul <= wLimit) {
            System.err.println("pack(" + wLimit + ", " + iFrom + ", " + iTo + ")");
            var res = (1 << (iTo - iFrom)) - 1;
            dp_i.put(wLimit, res);
            return res;
        }

        // 현재 아이템을 선택할 수 없는 경우
        if (wLimit < w) {
            var res = pack(wLimit, iFrom + 1, iTo);
            dp_i.put(wLimit, res);
            return res;
        }
        // 이미 계산된 경우
        if (dp_i.containsKey(wLimit)) {
            return dp_i.get(wLimit);
        }
        // 계산이 필요한 경우
        // 1. iFrom-index 아이템을 선택하는 경우
        var case1 = pack(wLimit - w, iFrom + 1, iTo) + 1;
        // 2. iFrom-index 아이템을 선택하지 않는 경우
        var case2 = pack(wLimit, iFrom + 1, iTo);
        var res = case1 + case2;
        dp_i.put(wLimit, res);
        return res;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
