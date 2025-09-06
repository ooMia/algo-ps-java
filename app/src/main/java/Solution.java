import java.util.Arrays;

class Solution {

    final int size;
    final int[][] grid;

    Solution(int size, int[][] grid) {
        this.size = size;
        this.grid = grid;
    }

    int solution() {
        if (size == 1)
            return grid[0][0];

        return solve(0, 0, size);
    }

    int solve(int r, int c, int size) {
        if (size == 1) return grid[r][c];
        if (size == 2) {
            int[] values = {grid[r][c], grid[r][c + 1], grid[r + 1][c], grid[r + 1][c + 1]};
            Arrays.sort(values);
            return values[1];
        }

        int midR = r + size / 2, midC = c + size / 2;
        int[] values = { solve(r, c, size / 2), solve(r, midC, size / 2), solve(midR, c, size / 2), solve(midR, midC, size / 2) };
        Arrays.sort(values);
        return values[1];
    }
}