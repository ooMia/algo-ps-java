class Solution {

    // pair of integers which sum to the smallest absolute value
    int[] res;

    public int[] solution(int[] numbers) {
        int iFirst = 0, iLast = numbers.length - 1;
        res = new int[] { numbers[iFirst], numbers[iLast] };

        while (iFirst < iLast) {
            int v1 = numbers[iFirst];
            int v2 = numbers[iLast];
            int sum = v1 + v2;

            System.err.println("Updating result: " + v1 + " + " + v2 + " = " + sum);
            if (sum == 0) {
                res[0] = v1;
                res[1] = v2;
                return res;
            } else {
                int prevAbsSum = Math.abs(res[0] + res[1]);
                int currentAbsSum = Math.abs(sum);
                if (currentAbsSum < prevAbsSum) {
                    res[0] = v1;
                    res[1] = v2;
                }
            }

            if (sum > 0) {
                iLast--;
            } else {
                iFirst++;
            }
        }
        return res;
    }

}