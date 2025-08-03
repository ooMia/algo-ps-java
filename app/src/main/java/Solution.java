class Solution {

    final String input;

    Solution(String input) {
        this.input = input;
    }
    
    public int solve() {
        int count = 0;
        int i = 0;
        while (i < input.length()) {
            var next = nextIndex(i);
            if (next == i + 4) {
                count++;
            }
            i = next;
        }
        return count;
    }

    int nextIndex(int iFrom) {
        // iFrom부터 시작해서 한 글자씩 검색하며
        // "DKSH"인지 확인하고
        // 만약 "DKSH"라면 iFrom + 4를 반환하고
        // 아니라면 iFrom + 1을 반환한다.
        if (input.startsWith("DKSH", iFrom)) {
            return iFrom + 4;
        }
        return iFrom + 1;
    }
}