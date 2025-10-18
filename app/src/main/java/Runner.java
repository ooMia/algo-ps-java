import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final BufferedWriter bw;

    final int nInputs;
    final int destId;
    final Solution.Graph graph;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        var reader = new Reader(br);
        try {
            int[] line = reader.readInts();
            this.destId = line[0];
            this.nInputs = line[1];

            if (nInputs > 0) {
                int[][] paths = new int[nInputs][];
                for (int i = 0; i < nInputs; ++i) {
                    paths[i] = reader.readInts();
                }
                graph = new Solution.Graph(destId, paths);
            } else {
                graph = null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(destId, graph);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}