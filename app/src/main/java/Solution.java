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
        if (h < 10) return toString_일의_자리(h);
        return toString_십대(h);
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
                yield toString_십대(m) + " minutes";
        };
    }

    private String toString_십대(int m) {
        return switch (m) {
            case 10:
                yield "ten";
            case 11:
                yield "eleven";
            case 12:
                yield "twelve";
            case 13:
                yield "thirteen";
            case 14:
                yield "fourteen";
            case 15:
                yield "fifteen";
            case 16:
                yield "sixteen";
            case 17:
                yield "seventeen";
            case 18:
                yield "eighteen";
            case 19:
                yield "nineteen";
            default:
                yield 일반화된_규칙(m);
        };
    }

    // assume m in [1, 9] or [20, 29]
    private String 일반화된_규칙(int m) {
        var sb = new StringBuilder();
        int 일의_자리 = m % 10, 십의_자리 = m / 10;
        if (십의_자리 == 2) sb.append("twenty");
        if (일의_자리 > 0) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(toString_일의_자리(일의_자리));
        }
        return sb.toString();
    }

    private String toString_일의_자리(int m) {
        return switch (m) {
            case 1:
                yield "one";
            case 2:
                yield "two";
            case 3:
                yield "three";
            case 4:
                yield "four";
            case 5:
                yield "five";
            case 6:
                yield "six";
            case 7:
                yield "seven";
            case 8:
                yield "eight";
            case 9:
                yield "nine";
            default:
                yield "";
        };
    }
}