class Solution {
    int solution(int A, int B) {
        int res = 1;
        for (int i = 0; i < B; ++i) {
            res *= A;
            res %= 10;
        }
        return res == 0 ? 10 : res;
    }
}