import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final IAbilityBoard board;
    int min = Integer.MAX_VALUE;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            this.N = reader.readInts()[0];
            int[][] S = new int[N][N];
            for (int i = 0; i < N; ++i) {
                S[i] = reader.readInts();
            }
            this.board = new AbilityBoard(N, S);
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
        boolean[] teamCase = new boolean[N];

        final int pivot = 0;
        teamCase[pivot] = true;

        dfs(1, pivot, teamCase);
        sb.append(min).append('\n');
    }

    private void dfs(int size, int lastAddedIndex, boolean[] isTeam) {
        if (size == N / 2) {
            this.min = Math.min(this.min, board.absScoreDiff(isTeam));

            String trueIndices = java.util.stream.IntStream.range(0, isTeam.length)
                .filter(i -> isTeam[i])
                .mapToObj(String::valueOf)
                .collect(java.util.stream.Collectors.joining(" "));
            String falseIndices = java.util.stream.IntStream.range(0, isTeam.length)
                .filter(i -> !isTeam[i])
                .mapToObj(String::valueOf)
                .collect(java.util.stream.Collectors.joining(" "));
            System.err.println("True: " + trueIndices + " False: " + falseIndices + " Score: " + board.absScoreDiff(isTeam));

            return;
        }

        var sizeNeeded = N / 2 - size;

        for (int i = lastAddedIndex + 1; i <= N - sizeNeeded; ++i) {
            isTeam[i] = true;
            dfs(size + 1, i, isTeam);
            isTeam[i] = false;
        }
    }
}

interface IRunner {
    void run();

    void flush();
}
