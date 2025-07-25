import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
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
        int q1, q2, q3, q4, axis;
        q1 = q2 = q3 = q4 = axis = 0;
        for (int n = 0; n < N; ++n) {
            var input = reader.readInts();
            int x = input[0];
            int y = input[1];
            if ( x == 0 || y == 0){
                axis++;
            } else if (x > 0 && y > 0) {
                q1++;
            } else if (x < 0 && y > 0) {
                q2++;
            } else if (x < 0 && y < 0) {
                q3++;
            } else {
                q4++;
            }
        }
        sb.append("Q1: ").append(q1).append("\n");
        sb.append("Q2: ").append(q2).append("\n");
        sb.append("Q3: ").append(q3).append("\n");
        sb.append("Q4: ").append(q4).append("\n");
        sb.append("AXIS: ").append(axis).append("\n");
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
