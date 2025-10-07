import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public String solution(
        int N, // 수강 과목 수
        int M, // 요구 과목 수
        int K, // 공개한 과목 수
        Map<String, Integer> dict, // 수강 과목과 점수
        String[] keys // 평가에 들어가야 하는 과목들
    ) {
        int res = 0;
        // 1. keys를 돌면서 dict의 값 더하기
        for (String key : keys) {
            res += dict.getOrDefault(key, 0);
            dict.remove(key);
        }

        List<Integer> orderedScores = dict.values().stream().sorted().collect(Collectors.toList());
        int goal = M - K;
        int iLast = orderedScores.size() - 1;

        // 2. 최솟값 구하기 (M - K)개
        int min = 0;
        for (int i = 0; i < goal; ++i) {
            min += orderedScores.get(i);
        }

        // 3. 최댓값 구하기 (M - K)개
        int max = 0;
        for (int i = iLast; i > iLast - goal; --i) {
            max += orderedScores.get(i);
        }
        return String.format("%d %d", min + res, max + res);
    }
}