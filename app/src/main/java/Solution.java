class Solution {

    final static int maxClassNumber = 1_000_000;
    final static int[] have = new int[maxClassNumber + 1];  // have[i] = 수업 i를 가진 학생 수
    final static int[] want = new int[maxClassNumber + 1];  // want[i] = 수업 i를 원하는 학생 수

    // N: 학생 수
    // cur: i번 학생의 현재 수업 배열
    // seek: i번 학생의 원하는 수업 배열
    public int solution(int N, int[] cur, int[] seek) {
        // 각 수업별로 현재 가진 학생 수와 원하는 학생 수 계산
        for (int i = 0; i < N; ++i) {
            ++have[cur[i]];
            ++want[seek[i]];
        }

        // 각 수업별로 교환 가능한 학생 수
        // ** maxClassId 만큼만 순회하는 건, max 연산 오버헤드 때문에 생각보다 느림
        int canExchange = 0;
        for (int i = 1; i <= maxClassNumber; ++i) {
            canExchange += Math.min(have[i], want[i]);
        }

        return N - canExchange;
    }
}