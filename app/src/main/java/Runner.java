import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.BitSet;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nAlphabets = 'z' - 'a' + 1;
    final int Q;
    final int[][] cumuls; // cumul[alphabet - 'a'][index]

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            char[] S = reader.line().toCharArray();
            cumuls = new int[nAlphabets][S.length];
            Q = reader.readInts()[0];
            sb.ensureCapacity(Q * S.length / nAlphabets);

            BitSet uniqueChars = new BitSet('z' - 'a' + 1);
            {
                char next = S[0];
                uniqueChars.set(next - 'a');
                cumuls[next - 'a'][0] = 1;
            }
            for (int i = 1; i < S.length; ++i) {
                char next = S[i];
                uniqueChars.set(next - 'a');
                for (int j = uniqueChars.nextSetBit(0); j >= 0; j = uniqueChars.nextSetBit(j + 1)) {
                    cumuls[j][i] = cumuls[j][i - 1] + (next == j + 'a' ? 1 : 0);
                }
            }
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
        for (int i = 0; i < Q; ++i) {
            var input = reader.line().split(" ");
            char c = input[0].charAt(0);
            int iFrom = Integer.parseInt(input[1]);
            int iTo = Integer.parseInt(input[2]);
            int found = sum(c, iFrom, iTo);
            sb.append(found).append('\n');
        }
    }

    private int sum(char c, int iFrom, int iTo) {
        if (iFrom <= 0) {
            return cumuls[c - 'a'][iTo];
        }
        return cumuls[c - 'a'][iTo] - cumuls[c - 'a'][iFrom - 1];
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}