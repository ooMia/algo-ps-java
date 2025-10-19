import java.util.Arrays;

class Solution {
    public long solution(int nClasses, int nStudents, long[] numbers) {
        for (int i = 1; i < numbers.length; ++i) {
            numbers[i] += numbers[i - 1];
        }
        Arrays.sort(numbers);
        System.err.println(Arrays.toString(numbers));

        long sum = 0;
        int i = 0;
        while (i < nStudents) {
            sum += numbers[numbers.length - 1 - i];
            System.err.println(sum);
            i++;
        }
        return sum;
    }
}
