class Solution {
    public int solution(int N) {
        // 1. int to binaryString
        String binaryString = Integer.toBinaryString(N);
        // 2. reverse string
        String reversedString = new StringBuilder(binaryString).reverse().toString();
        // 3. binaryString to int
        return Integer.parseInt(reversedString, 2);
    }
}