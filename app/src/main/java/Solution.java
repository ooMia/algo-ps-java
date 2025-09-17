import java.util.Map;

class Solution {
    int solution(Map<String, Integer> closet) {
        int res = 1;
        for (int cnt : closet.values()) {
            res *= (cnt + 1); // 해당 종류를 입지 않는 경우 포함
        }
        return res - 1; // 아무것도 입지 않는 경우 제외
    }
}