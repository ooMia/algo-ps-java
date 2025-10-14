class Solution {
    public int solution(int[][] records) {
        int maxCount = -1;
        int cur = 0;
        for (int[] record : records) {
            cur += record[1];
            cur -= record[0];
            maxCount = Math.max(maxCount, cur);
        }
        return maxCount;
    }
}