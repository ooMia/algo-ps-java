import java.util.Map;

class Solution {

    final Map<String, Integer> likes;

    Solution(int N, String[] names, String[][] likes) {
        this.likes = new java.util.HashMap<>(N);
        for (int i = 0; i < N; ++i)
            this.likes.put(names[i], 0);
        for (int i = 0; i < N; ++i)
            for (String like : likes[i])
                this.likes.put(like, this.likes.getOrDefault(like, 0) + 1);
    }

    public String solution() {
        var l = likes.entrySet().stream()
                .sorted((o1, o2) -> {
                    int cmp = Integer.compare(o2.getValue(), o1.getValue());
                    return cmp != 0 ? cmp : o1.getKey().compareTo(o2.getKey());
                });
        StringBuilder sb = new StringBuilder();
        l.forEach(e -> sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n'));
        return sb.toString();
    }
}