import java.util.Arrays;

class Solution {

    public String solution(char[] numbers) {
        Arrays.sort(numbers);
        if (numbers[0] != '0')
            return "-1";

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i=numbers.length-1; i>=0; --i) {
            var c = numbers[i];
            sum += c - '0';
            sb.append(c);
        }

        return sum % 3 == 0 ? sb.toString() : "-1";
    }
}
