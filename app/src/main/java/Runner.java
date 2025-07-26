import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K; // N, K: location of A and B; 0 <= N, K <= 10^6
    final int[] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nk = reader.readInts();
            N = _nk[0];
            K = _nk[1];
            dp = new int[100_001];
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
        Queue<Data> q = new LinkedList<>();
        q.offer(new Data(N, 0));

        while (!q.isEmpty()) {
            Data current = q.poll();
            if (current.position == K) {
                sb.append(current.nMove).append('\n');
                int nAnswer = 1;
                while (!q.isEmpty()) {
                    Data next = q.poll();
                    if (next.position == K && next.nMove == current.nMove) {
                        nAnswer++;
                    }
                }
                sb.append(nAnswer).append('\n');
                return;
            }

            int[] modifier = { 1, -1, 2 };
            for (int mod : modifier) {
                int next = mod == 2 ? current.position * 2 : current.position + mod;
                if (next < 0 || 100_000 < next) {
                    continue;
                }
                if (dp[next] == 0 || current.nMove + 1 <= dp[next]) {
                    dp[next] = current.nMove + 1;
                    q.add(new Data(next, current.nMove + 1));
                }
            }
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

class Data {
    int position;
    int nMove;

    Data(int position, int nMove) {
        this.position = position;
        this.nMove = nMove;
    }
}