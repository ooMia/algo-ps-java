import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nLimit = 9;
    final Stack<History> history = new Stack<>();

    int[][] grid = new int[nLimit][nLimit];
    PriorityQueue<Data> tasks = new PriorityQueue<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        final int[][] cols = new int[nLimit][nLimit];
        final int[][][] blocks = new int[nLimit / 3][nLimit / 3][nLimit];
        try {
            for (int y = 0; y < nLimit; ++y) {
                grid[y] = reader.readInts();
                for (int x = 0; x < nLimit; ++x) {
                    int value = grid[y][x];
                    cols[x][y] = value;
                    blocks[y / 3][x / 3][(y % 3) * 3 + (x % 3)] = value;
                }
            }

            for (int y = 0; y < nLimit; ++y) {
                for (int x = 0; x < nLimit; ++x) {
                    if (grid[y][x] == 0) {
                        var data = new Data(y, x, grid[y], cols[x], blocks[y / 3][x / 3]);
                        tasks.offer(data);
                    }
                }
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
        while (!tasks.isEmpty()) {
            Data task = tasks.poll();
            int y = task.y, x = task.x;

            int possibilities = task.possibles.size();
            if (possibilities < 1) {
                var s = history.pop();
                this.grid = s.grid;
                this.tasks = s.tasks;
                continue;
            }

            if (task.possibles.size() > 1) {
                history.push(new History(grid, tasks, task));
            }

            int value = task.possibles.iterator().next();
            grid[y][x] = value;

            // System.err.println(task + " -> " + value);

            for (Data affected : task.update(value, tasks)) {
                tasks.remove(affected);
                tasks.add(affected);
            }
        }

        for (int y = 0; y < nLimit; ++y) {
            for (int x = 0; x < nLimit; ++x) {
                sb.append(grid[y][x]).append(' ');
            }
            sb.append('\n');
        }
    }
}

interface IRunner {
    void run();

    void flush();
}

class History {
    final int[][] grid = new int[9][9];
    final PriorityQueue<Data> tasks = new PriorityQueue<>();

    History(final int[][] _grid, final PriorityQueue<Data> _tasks, final Data _chosen) {
        for (int y = 0; y < 9; ++y) {
            System.arraycopy(_grid[y], 0, grid[y], 0, 9);
        }
        Data data = _chosen.clone();
        data.possibles.remove(data.possibles.iterator().next());
        tasks.add(data);
        for (Data task : _tasks) {
            tasks.add(task.clone());
        }
    }
}