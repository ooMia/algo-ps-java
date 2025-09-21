import java.util.LinkedList;
import java.util.List;

class Solution {
    int solution(int n, int[] numbers) {
        List<Integer> odds = new LinkedList<>();
        int res = 0;
        for (int num : numbers) {
            if ((num & 1) == 0) {
                res += num;
            } else {
                odds.add(num);
            }
        }
        odds.sort((a, b) -> b - a);
        // 한 번에 두 개씩 묶어서 더하기
        for (int i = 0; i + 1 < odds.size(); i += 2) {
            res += odds.get(i) + odds.get(i + 1);
        }
        return res;
    }
}