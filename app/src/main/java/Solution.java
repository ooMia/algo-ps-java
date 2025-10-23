class Solution {
    public int solution(int n) {
        return (n / 5) + (n % 5 == 0 ? 0 : 1);
    }
}
