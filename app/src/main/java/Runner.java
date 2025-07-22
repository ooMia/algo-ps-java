import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    int A, B, C; // res = A^B % C
    Map<Integer, Integer> dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _abc = reader.readInts();
            A = _abc[0];
            B = _abc[1];
            C = _abc[2];
            dp = new HashMap<>();
            dp.put(0, 1);
            dp.put(1, A % C);
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
        int res = power(A, B);
        sb.append(res).append('\n');
    }

    private int power(int a, int b) {
        if (dp.containsKey(b)) {
            return dp.get(b);
        }

        int half, res;
        if (b % 2 == 0) {
            half = power(a, b / 2);
            res = product(half, half);
            dp.put(b, res);
        } else {
            half = power(a, (b - 1) / 2);
            res = product(half, half);
            dp.put(b - 1, res);
            res = product(res, a % C);
        }
        System.err.println("power(" + a + ", " + b + ") = " + res);
        return res;
    }

    private int product(int a, int b) {
        return (int) (((long) a % C) * ((long) b % C) % C);
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
