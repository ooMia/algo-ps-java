class Solution {

    public int solution(int N, int[] cur, int[] seek) {
        // A_i: i번 학생이 교환하고 싶은 수업의 번호 (from)
        // B_i: i번 학생이 교환 받고 싶은 수업의 번호 (to)

        int count = N;
        for (int i = 0; i < N; ++i) {
            // 이미 원하는 수업을 가지고 있는 경우
            if (cur[i] == seek[i]) {
                --count;
                continue;
            }

            for (int other = i + 1; other < N; ++other) {
                if (cur[other] == seek[other]) continue;
                // 1. 내가 원하는 것을 다른 사람이 가진 경우
                if (seek[i] == cur[other]) {
                    cur[other] = cur[i];
                    --count;
                    break;
                }
                // 2. 상대가 원하는 것을 내가 가진 경우
                if (seek[other] == cur[i]) {
                    seek[other] = seek[i]; 
                    --count;
                    break;
                }
            }
        }
        return count;
    }
}