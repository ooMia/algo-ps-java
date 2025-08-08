import java.util.HashMap;
import java.util.Map;

class Solution {

    final int baseTime = 100;
    final int baseFee = 10;
    final int unitTime = 50;
    final int unitFee = 3;

    public String solution(Map<String, Integer> usage) {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> feeMap = new HashMap<>();
        for (var entry : usage.entrySet()) {
            String user = entry.getKey();
            int time = entry.getValue();
            int fee = calcFee(time);
            feeMap.put(user, fee);
        }

        var l = feeMap.entrySet().stream()
                .sorted(
                        (o1, o2) -> {
                            int cmp = Integer.compare(o2.getValue(), o1.getValue());
                            return cmp != 0 ? cmp : o1.getKey().compareTo(o2.getKey());
                        })
                .collect(java.util.stream.Collectors.toList());

        for (var entry : l) {
            String user = entry.getKey();
            int fee = entry.getValue();
            sb.append(user).append(' ').append(fee).append('\n');
        }
        return sb.toString();
    }

    int calcFee(int _time) {
        if (_time <= baseTime)
            return baseFee;
        int extra = _time - baseTime;
        int units = (extra + unitTime - 1) / unitTime; // 올림 처리
        return baseFee + units * unitFee;
    }
}