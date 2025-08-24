import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nNodes, rootId, nQueries;
    final List<Set<Integer>> edges;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.nNodes = input[0];
            this.rootId = input[1];
            this.nQueries = input[2];

            this.edges = new ArrayList<>(nNodes + 1);
            for (int i = 0; i < nNodes + 1; ++i) {
                edges.add(new HashSet<>());
            }

            for (int i = 1; i < nNodes; ++i) {
                var edge = reader.readInts();
                edges.get(edge[0]).add(edge[1]);
                edges.get(edge[1]).add(edge[0]);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution(nNodes, edges, rootId);
        for (int i = 0; i < nQueries; ++i) {
            var res = sol.solution(Integer.parseInt(reader.line()));
            sb.append(res).append('\n');
        }
    }
}