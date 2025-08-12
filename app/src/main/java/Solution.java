class Solution {

    public int solution(char[][] grid) {
        int count = 0;
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                if (grid[y][x] == 'F')
                    count += isWhite(y, x) ? 1 : 0;
            }
        }
        return count;
    }

    boolean isWhite(int y, int x) {
        return ((y + x) & 1) == 0;
    }

}
