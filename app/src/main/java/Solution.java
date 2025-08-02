import java.util.Arrays;

class Solution {

    // pair of integers which sum to the smallest absolute value
    int[] res;

    public int[] solution(int[] numbers) {
        // init
        int iFirst = 0, iLast = numbers.length - 1;
        res = new int[] { numbers[iFirst], numbers[iLast], numbers[iLast - 1] };

        while (iFirst < iLast) {
            int v1 = numbers[iFirst];
            int v2 = numbers[iLast];
            int sum = findLocalMinAbs(numbers, iFirst, iLast);

            int prevAbsSum = Math.abs(res[0] + res[1] + res[2]);
            int currentAbsSum = Math.abs(sum);
            if (currentAbsSum < prevAbsSum) {
                res[0] = v1;
                res[1] = v2;
                res[2] = sum - v1 - v2;
                if (currentAbsSum == 0) {
                    break;
                }
            }
        }

        Arrays.sort(res);
        return res;
    }

    int findLocalMinAbs(int[] numbers, int iFrom, int iEnd) {
        // iFrom < iMid < iEnd
        final int baseSum = numbers[iFrom++] + numbers[iEnd--];
        int iMid, nextSum, prevSum = Integer.MAX_VALUE;

        while (iFrom < iEnd) {
            iMid = (iFrom + iEnd) / 2;
            nextSum = baseSum + numbers[iMid];

            if (nextSum < 0) {
                iFrom = iMid + 1;
            } else if (nextSum > 0) {
                iEnd = iMid - 1;
            } else {
                return 0;
            }

            if (Math.abs(nextSum) < Math.abs(prevSum)) {
                prevSum = nextSum;
            }
        }

        return prevSum;
    }

}