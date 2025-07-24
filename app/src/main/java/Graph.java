import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Graph implements IGraph {
    private final static int INF = Integer.MAX_VALUE;

    final int V, E; // V: number of vertices (1...V), E: number of edges
    final Map<Integer, Map<Integer, Integer>> graph; // from -> (to -> weight)
    final Map<Integer, int[]> dist; // from -> (to -> weight)

    Graph(int V, int E) {
        this.V = V;
        this.E = E;
        this.graph = new HashMap<>(E);
        this.dist = new HashMap<>();
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        graph.computeIfAbsent(from, k -> new HashMap<>())
                .merge(to, weight, Math::min);
    }

    @Override
    public Integer route(int from, int to) {
        if (dist.get(from) == null) {
            dist.put(from, dijkstra(from));
        }
        var res = dist.get(from)[to];
        return res == INF ? null : res;
    }

    private int[] dijkstra(int from) {
        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; ++i)
            dist[i] = INF;
        dist[from] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] { from, 0 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u])
                continue;
            if (!graph.containsKey(u))
                continue;
            for (var entry : graph.get(u).entrySet()) {
                int v = entry.getKey(), w = entry.getValue();
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new int[] { v, dist[v] });
                }
            }
        }
        return dist;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    public Integer getWeight(int from, int to) {
        return graph.getOrDefault(from, Map.of()).getOrDefault(to, null);
    }
}

interface IGraph {
    void addEdge(int from, int to, int weight); // add edge with weight, if exists, keep the minimum weight

    Integer route(int from, int to);
}
