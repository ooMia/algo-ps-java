import java.util.List;

class Solution {
    // M: 터널 안에 들어있는 차량의 수
    // datas: 특정 시간대에 입구를 통과한 차량의 수와 출구를 통과한 차량의 수
    public int solution(int M, List<Record> datas) {
        int maxCount = M;
        int currentCount = M;
        for (var data : datas) {
            currentCount = data.getTunnelCount(currentCount);
            if (currentCount < 0) return 0;
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
}