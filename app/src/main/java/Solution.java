class Solution {

    public int solution(int N, String name) {
        int res = 0;
        for (char c : name.toCharArray())
            res += score(c);
        return res;
    }

    int score(char c) {
        return c - 'A' + 1;
    }
}