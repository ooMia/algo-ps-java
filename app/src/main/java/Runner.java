import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int T;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            T = reader.readInts()[0];
            for (int i = 0; i < T; ++i) {
                reader.skipLine();
                Set<Integer> setA = reader.readIntegers().stream().collect(Collectors.toSet());
                Set<Integer> setB = reader.readIntegers().stream().collect(Collectors.toSet());

                boolean isASubsetOfB = setB.containsAll(setA);
                boolean isBSubsetOfA = setA.containsAll(setB);

                if (isASubsetOfB && isBSubsetOfA) {
                    sb.append("=\n");
                } else if (isASubsetOfB) {
                    sb.append("<\n");
                } else if (isBSubsetOfA) {
                    sb.append(">\n");
                } else {
                    sb.append("?\n");
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
    public void run() {
    }
}

interface IRunner {
    void run();

    void flush();
}
