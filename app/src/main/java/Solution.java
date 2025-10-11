class Solution {
    public int solution(int N, int[] heights) {
        // N개의 폭죽 더미가 있다

        // 다음 작업을 N-2번 수행한다

        // 양 끝 폭죽 더미를 제외한 폭죽 더미를 하나 고른다
        // 해당 더미의 폭죽을 모두 터뜨린다
        // 양 옆의 더미는 높이가 1 감소한다

        // 조건에 따라 양 끝 더미는 반드시 남게 되니까
        // 핵심은 양 끝의 더미의 균형을 맞추는 것

        // N = 3일 때, 반드시 양쪽 모두의 개수가 줄어든다는 것을 생각하면
        // 실질적으로 균형을 맞추는 데 사용할 수 있는 기회는 N-3번
        // 마지막은 무조건 둘 다 1씩 감소한다 
        return foo(N - 3, heights[0], heights[N - 1]) - 1;
    }

    private int foo(int nSub, int n1, int n2) {
        // nSub 횟수만큼 각 수에 뺄셈 연산을 취할 수 있다.
        // 모든 연산을 마친 후에는 두 수의 최댓값을 반환한다.

        // 1. 두 수의 대소관계를 파악한다.
        int big = Math.max(n1, n2);
        int small = Math.min(n1, n2);

        // 2. nSub 횟수 안에 두 수를 같게 만들 수 있는지 확인한다.
        int need = big - small;
        boolean canEqual = need <= nSub;

        // 3-1. 두 수가 같아질 수 있으면,
        // 두 수를 같게 만든 이후에 남은 nSub를 둘로 나누어 균등하게 뺄셈
        // 작은 수 - (nSub - need) / 2 반환
        // 3-2. 두 수가 같아질 수 없으면, 큰 수 - nSub 반환
        return canEqual ? small - (nSub - need) / 2 : big - nSub;
    }
}