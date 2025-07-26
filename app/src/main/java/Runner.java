import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final PriorityQueue<Integer> pq = new PriorityQueue<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ab = reader.readInts();
            N = _ab[0];
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
        List<Integer> inputs = reader.lines().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        for (int i : inputs) {
            process(i);
        }
    }

    private void process(int o) {
        if (o == 0) {
            Integer res = pq.poll();
            sb.append(res != null ? res : 0).append("\n");
        } else {
            pq.offer(o);
        }
    }

}

interface IRunner {
    void run() throws IOException;

    void flush();
}
