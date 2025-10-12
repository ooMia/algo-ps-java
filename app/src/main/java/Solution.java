class Solution {

    static String oClock = "o' clock";
    static String m_1_30 = "past";
    static String m_31_59 = "to";

    public String solution(int h, int m) {
        if (m == 0)
            return String.format("%s o' clock", hourToString(h));

        var n_hour = m <= 30 ? h : (h + 1 == 13 ? 1 : h + 1);
        var hour = hourToString(n_hour);
        var middle = m <= 30 ? m_1_30 : m_31_59;
        var n_minute = m <= 30 ? m : 60 - m;
        var minute = minuteToString(n_minute);
        return String.format("%s %s %s", minute, middle, hour);
    }

    String hourToString(int h) {
        return StringNumber.of(h);
    }

    String minuteToString(int m) {
        // 내부 entryPoint
        return switch (m) {
            // 0, 15, 30, 45 케이스
            // 그 다음 [11, 19] 케이스
            // 나머지는 따로 처리
            case 0:
                yield "o' clock";
            case 30:
                yield "half";
            case 15: // 45는 사전에 변환시킨 상태에서 호출해야 함
                yield "quarter";
            default:
                yield StringNumber.of(m) + (m == 1 ? " minute": " minutes");
        };
    }
}