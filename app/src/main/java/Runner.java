import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int V, E; // V: number of vertices (1...V), E: number of edges
    final int K; // K: starting vertex
    final Graph graph; // from -> (to -> weight)

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ve = reader.readInts();
            V = _ve[0];
            E = _ve[1];
            K = reader.readInts()[0];
            graph = new Graph(V, E);
            for (int i = 0; i < E; ++i) {
                var line = reader.readInts();
                graph.addEdge(line[0], line[1], line[2]);
            }
            sb.ensureCapacity(V * 2);
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
        for (int v = 1; v <= V; ++v) {
            Integer route = graph.route(K, v);
            if (route == null) {
                sb.append("INF\n");
            } else {
                sb.append(route).append('\n');
            }
        }
    }
}


interface IRunner {
    void run() throws IOException;

    void flush();
}
