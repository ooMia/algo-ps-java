import java.util.HashSet;

class Solution {
    public int solution(int[] s1, int[] s2) {
        // s1 집합 - s2 집합 (차집합으로 사라진 원소 찾기)
        // s1에서 s2에 없는 원소를 찾아야 함
        // s1과 s2는 정렬되어 있지 않음

        HashSet<Integer> set = new HashSet<>();
        for (int num : s1) {
            set.add(num);
        }
        for (int num : s2) {
            if (set.contains(num)) {
                set.remove(num);
            }
        }
        return set.isEmpty() ? -1 : set.iterator().next();
    }
}