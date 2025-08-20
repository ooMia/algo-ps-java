import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {

    final int N, M;
    final int[][] priorities;

    Map<Integer, Node> nodes;

    Solution(int N, int M, int[][] priorities) {
        this.N = N;
        this.M = M;
        this.priorities = priorities;

        this.nodes = new TreeMap<>();
    }

    public String solution() {
        for (var p : priorities) {
            int a = p[0], b = p[1];
            if (!nodes.containsKey(a))
                nodes.put(a, new Node(a));
            nodes.get(a).addChild(b);
        }

        var sb = new StringBuilder();
        for (int i = 1; i <= N; ++i) {
            if (!nodes.containsKey(i)) {
                sb.append(i).append(' ');
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (var entry : nodes.entrySet()) {
            if (entry.getValue().inDegree == 0)
                pq.add(entry.getKey());
        }
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(' ');
            for (int childId : nodes.get(cur).children) {
                Node child = nodes.get(childId);
                child.inDegree--;
                if (child.inDegree == 0)
                    pq.add(childId);
            }
        }
        return sb.toString();
    }

    class Node {
        final int id;
        final List<Integer> children;
        int inDegree;

        Node(int id) {
            this.id = id;
            this.children = new ArrayList<>();
            this.inDegree = 0;
        }

        void addChild(int childId) {
            children.add(childId);
            if (!nodes.containsKey(childId)) {
                nodes.put(childId, new Node(childId));
            }
            nodes.get(childId).inDegree++;
        }
    }
}
