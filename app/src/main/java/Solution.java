import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    final char[][] grid;

    Solution(char[][] grid) {
        this.grid = grid;
    }

    public String solution() {
        PriorityQueue<Paper> queue = new PriorityQueue<>();
        queue.add(new Paper(0, 0, grid.length));

        int nWhite = 0, nBlue = 0;
        while (!queue.isEmpty()) {
            Paper paper = queue.poll();
            int color = paper.color();

            if (color == -1) { // mixed
                if (paper.size == 2) {
                    int Y = paper.minY, X = paper.minX;
                    for (int y = Y; y < Y + 2; ++y) {
                        for (int x = X; x < X + 2; ++x) {
                            if (grid[y][x] == '0') {
                                ++nWhite;
                            } else {
                                ++nBlue;
                            }
                        }
                    }
                } else
                    queue.addAll(paper.split());
            } else if (color == 0) {
                ++nWhite;
            } else if (color == 1) {
                ++nBlue;
            }
        }
        return String.format("%d\n%d", nWhite, nBlue);
    }

    class Paper implements Comparable<Paper> {
        final int minY, maxY, minX, maxX, size;

        Paper(int minY, int minX, int size) {
            this.minY = minY;
            this.minX = minX;
            this.size = size;
            this.maxY = minY + size - 1;
            this.maxX = minX + size - 1;
        }

        int color() {
            // -1: mixed, 0: white, 1: blue
            char base = grid[minY][minX];
            for (int y = minY; y <= maxY; ++y) {
                for (int x = minX; x <= maxX; ++x) {
                    if (grid[y][x] != base)
                        return -1; // mixed
                }
            }
            return base - '0';
        }

        Collection<Paper> split() {
            int half = size / 2;
            return List.of(
                    new Paper(minY, minX, half),
                    new Paper(minY, minX + half, half),
                    new Paper(minY + half, minX, half),
                    new Paper(minY + half, minX + half, half));
        }

        @Override
        public int compareTo(Solution.Paper o) {
            return Integer.compare(this.size, o.size);
        }
    }
}