class Solution {
    int solution(int goal) {
        int cur = 64, num = 0;
        while (goal != 0) {
            if (cur <= goal) {
                goal -= cur;
                ++num;
            }
            cur /= 2;
        }
        return num;
    }
}