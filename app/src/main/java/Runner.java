import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
                int N, P;
                int[] inputs = reader.readInts();
                N = inputs[0];
                P = inputs[1];

                int floor = 0;
                int step = 0;
                while (N-- > 0) {
                    ++step;
                    if (floor + step != P) {
                        floor += step;
                    }
                }
                sb.append(floor).append("\n");
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
