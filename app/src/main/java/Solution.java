import java.util.List;

class Solution {

    public String solution(int N, int K) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        List<Integer> people = new java.util.LinkedList<>();
        for (int j = 1; j <= N; j++) {
            people.add(j);
        }

        int i = 0;
        while (people.size() > 0) {
            i = (i + K - 1) % people.size();
            sb.append(people.remove(i));
            if (people.size() > 0) {
                sb.append(", ");
            }
        }
        sb.append(">");
        return sb.toString();
    }
}