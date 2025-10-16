class Solution {

    // 수열 A가 주어지면, 다음과 같이 수열 B를 만든다.
    // (누적 평균) = (누적 합) / 개수

    // 따라서, B가 주어지면 A에 대한 누적 합 배열을 구할 수 있다.
    // 각 요소 * (현재 인덱스 + 1) = (현재까지의 A의 누적 합)

    // 각 누적합 배열의 요소는 이전 요소와의 차이를 통해
    // 원래 수열 A의 요소를 구할 수 있다.

    public String solution(int N, long[] numbers) {
        for (int i = numbers.length - 1; i > 0; --i) {
            numbers[i] = numbers[i] * (i + 1) - numbers[i - 1] * i;
        }
        StringBuilder sb = new StringBuilder();
        for (long num : numbers) {
            sb.append(num).append(' ');
        }
        return sb.toString();
    }
}