import java.util.Map;

class Solution {
    final int N;
    final Map<String, Integer> users;

    Solution(int N, Map<String, Integer> users) {
        this.N = N;
        this.users = users;
    }

    public String solution() {
        return users.keySet().iterator().next();
    }
}