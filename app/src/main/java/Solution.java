class Solution {
    /**
     * @param A 피로도 증가량
     * @param B 처리량
     * @param C 피로도 감소량
     * @param M 최대 피로도
     * @return 피로도 M을 넘지 않고 24시간 내에 처리할 수 있는 최대 처리량
     */
    public int solution(int A, int B, int C, int M) {
        int time, fatigue, workDone;
        time = fatigue = workDone = 0;
        while (time < 24) {
            if (fatigue + A > M) {
                fatigue = Math.max(0, fatigue - C);
            } else {
                fatigue += A;
                workDone += B;
            }
            ++time;
        }
        return workDone;
    }
}