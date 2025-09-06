class Solution {
    String solution(int N, int[] numbers) {
        long numerator = 0, denominator = 1;

        // 주어진 자연수의 역수의 합
        for (long number: numbers) {
            long lcm = lcm(denominator, number);

            numerator *= (lcm / denominator);
            numerator += (lcm / number);
            denominator = lcm;
        }

        long gcd = gcd(numerator, denominator); // 기약분수화
        numerator /= gcd;
        denominator /= gcd;

        // 결과 출력
        return String.format("%s/%s", denominator, numerator);
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}