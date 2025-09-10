class Solution {
    String solution(int[] A, int[] C) {
        int[] res = new int[3];
        res[0] = C[0] - A[2];
        res[1] = C[1] / A[1];
        res[2] = C[2] - A[0];
        return String.format("%d %d %d", res[0], res[1], res[2]);
    }
}