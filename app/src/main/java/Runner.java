import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.BitSet;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[] nums;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nm = reader.readInts();
            N = _nm[0];
            M = _nm[1];
            nums = reader.readInts();
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
        long[] cumuls = new long[N];
        int[] remains = new int[M];
        BitSet rKey = new BitSet(M);
        {
            cumuls[0] = nums[0] % M;
            int key = (int) (cumuls[0] % M);
            remains[key]++;
            rKey.set(key);
        }
        for (int i = 1; i < N; ++i) {
            cumuls[i] = cumuls[i - 1] + nums[i];
            int key = (int) (cumuls[i] % M);
            remains[key]++;
            rKey.set(key);
        }

        long result = remains[0];
        for (int i = rKey.nextSetBit(0); i >= 0; i = rKey.nextSetBit(i + 1)) {
            if (remains[i] >= 2) {
                result += nC2(remains[i]);
            }
        }
        sb.append(result).append('\n');
    }

    private long nC2(int n) {
        return (long) n * (n - 1) / 2;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
