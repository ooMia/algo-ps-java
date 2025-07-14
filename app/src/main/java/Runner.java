import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, V = 1;
    final Map<Integer, Set<Integer>> graph = new HashMap<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            this.N = reader.readInts()[0];
            reader.skipLine();
            reader.lines().forEach(line -> {
                var parts = line.split(" ");
                int source = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                graph.computeIfAbsent(source, k -> new HashSet<>()).add(dest);
                graph.computeIfAbsent(dest, k -> new HashSet<>()).add(source);
            });
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
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(V);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (visited[v])
                continue;
            bfs(v, visited, queue);
        }

        int count = -1;
        for (int i = 1; i <= N; ++i) {
            if (visited[i]) {
                ++count;
            }
        }
        sb.append(count).append("\n");
    }

    private void bfs(int v, boolean[] visited, Queue<Integer> queue) {
        visited[v] = true;
        System.err.println("Visiting: " + v);

        Iterator<Integer> neighbors = graph.getOrDefault(v, new HashSet<>()).iterator();
        if (!neighbors.hasNext()) {
            return;
        }
        while (neighbors.hasNext()) {
            queue.add(neighbors.next());
        }
    }

}

interface IRunner {
    void run();

    void flush();
}
