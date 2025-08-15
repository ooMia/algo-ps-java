import java.util.Arrays;

class Solution {
    final int N;
    final int[] numbers;

    final int[][] dp; // -1: unknown, 1: possible, 0: impossible

    public Solution(int N, int[] numbers) {
        this.N = N;
        this.numbers = numbers;
        this.dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
    }

    public int solution(int iFrom, int iTo) {
        if (iFrom == iTo)
            return 1;
        if (dp[iFrom][iTo] >= 0)
            return dp[iFrom][iTo];

        var res = isPalindrome(iFrom, iTo) ? 1 : 0;

        // 결과 전파
        if (res == 1) {
            // 내부도 팰린드롬
            while (iFrom < iTo) {
                if (dp[iFrom][iTo] >= 0)
                    break;
                else
                    dp[iFrom++][iTo--] = res;
            }
        } else {
            // 외부도 팰린드롬 아님
            while (0 <= iFrom && iTo < N) {
                if (dp[iFrom][iTo] >= 0)
                    break;
                else
                    dp[iFrom--][iTo++] = res;
            }
        }
        return res;
    }

    boolean isPalindrome(int iFrom, int iTo) {
        while (iFrom < iTo) {
            if (dp[iFrom][iTo] >= 0)
                return dp[iFrom][iTo] == 1;
            if (numbers[iFrom] != numbers[iTo])
                return false;
            iFrom++;
            iTo--;
        }
        return true;
    }
}