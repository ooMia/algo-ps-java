class Solution {
    public String solution(int N, int M) {
        return (N % 3 == 0 || M % 3 == 0) ? "YES" : "NO";
    }
}