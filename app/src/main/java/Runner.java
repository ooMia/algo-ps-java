import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int A, B;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ab = reader.readInts();
            A = _ab[0];
            B = _ab[1];
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
        Integer res = solve(A, B, 1);
        res = res == null ? -1 : res;
        sb.append(res).append('\n');
    }

    private Integer solve(long a, long b, int cnt) {
        System.err.println("solve(" + a + ", " + b + ", " + cnt + ")");
        // operation 1. multiply by 2
        // operation 2. multiply by 10 and add 1
        if (b < a) {
            return null;
        }
        if (a == b) {
            return cnt;
        }
        Integer case1 = solve(a * 2, b, cnt + 1);
        if (case1 == null) {
            case1 = Integer.MAX_VALUE;
        }
        Integer case2 = solve(a * 10 + 1, b, cnt + 1);
        if (case2 == null) {
            case2 = Integer.MAX_VALUE;
        }

        int res = Math.min(case1, case2);
        if (res == Integer.MAX_VALUE) {
            return null;
        }
        return res;
    }

}

interface IRunner {
    void run() throws IOException;

    void flush();
}
