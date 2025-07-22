import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] dp;
    final boolean[] visited;
    final Map<Integer, List<Integer>> nodes;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            dp = new int[N + 1];
            visited = new boolean[N + 1];
            nodes = new TreeMap<>();
            for (int i = 0; i < N - 1; ++i) {
                var line = reader.readIntegers();
                var nodeA = line.get(0);
                var nodeB = line.get(1);

                if (!nodes.containsKey(nodeA)) {
                    nodes.put(nodeA, new ArrayList<>());
                }
                nodes.get(nodeA).add(nodeB);

                if (!nodes.containsKey(nodeB)) {
                    nodes.put(nodeB, new ArrayList<>());
                }
                nodes.get(nodeB).add(nodeA);
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
        visit(1);

        for (int i = 2; i <= N; ++i) {
            sb.append(dp[i]).append('\n');
        }
    }

    private void visit(int node) {
        visited[node] = true;

        for (int child : nodes.get(node)) {
            if (visited[child]) {
                continue;
            }
            dp[child] = node;
            visit(child);
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
