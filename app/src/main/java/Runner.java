import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int M;
    final Data input = new Data();
    final java.util.Set<Integer> set = new java.util.HashSet<>();
    final java.util.List<Integer> allSet = java.util.stream.IntStream.range(1, 21).boxed()
            .collect(java.util.stream.Collectors.toList());

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            this.M = reader.readInts()[0];
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
        for (int i = 0; i < M; ++i) {
            input.set(reader.line());
            consume(input);
        }
    }

    private void consume(Data data) {
        switch (data.op) {
            case add:
                set.add(data.operand);
                break;
            case remove:
                set.remove(data.operand);
                break;
            case check:
                sb.append(set.contains(data.operand) ? 1 : 0).append("\n");
                break;
            case toggle:
                if (set.contains(data.operand)) {
                    set.remove(data.operand);
                } else {
                    set.add(data.operand);
                }
                break;
            case all:
                set.addAll(allSet);
                break;
            case empty:
                set.clear();
                break;
        }
    }

}

interface IRunner {
    void run() throws IOException;

    void flush();
}

class Data {
    Operation op;
    int operand;

    enum Operation {
        add, remove, check, toggle, all, empty
    }

    void set(String input) {
        String[] parts = input.split(" ");
        this.op = Operation.valueOf(parts[0]);
        this.operand = parts.length > 1 ? Integer.parseInt(parts[1]) : -1;
    }
}