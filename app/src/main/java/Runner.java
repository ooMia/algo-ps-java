import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final Map<String, String[]> nodes;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = reader.readInts()[0];
            nodes = new HashMap<>(N);
            for (int i = 0; i < N; i++) {
                var line = reader.line().split(" ");
                nodes.put(line[0], new String[] { line[1], line[2] });
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
        preorder("A", 0);
        sb.append("\n");
        inorder("A", 0);
        sb.append("\n");
        postorder("A", 0);
        sb.append("\n");

    }

    private void preorder(String node, int nVisited) {
        // root - left - right
        if (nVisited == N || node.equals(".")) {
            return;
        }
        sb.append(node);
        preorder(nodes.get(node)[0], nVisited + 1);
        preorder(nodes.get(node)[1], nVisited + 1);
    }

    private void inorder(String node, int nVisited) {
        // left - root - right
        if (nVisited == N || node.equals(".")) {
            return;
        }
        inorder(nodes.get(node)[0], nVisited + 1);
        sb.append(node);
        inorder(nodes.get(node)[1], nVisited + 1);
    }

    private void postorder(String node, int nVisited) {
        // left - right - root
        if (nVisited == N || node.equals(".")) {
            return;
        }
        postorder(nodes.get(node)[0], nVisited + 1);
        postorder(nodes.get(node)[1], nVisited + 1);
        sb.append(node);
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}
