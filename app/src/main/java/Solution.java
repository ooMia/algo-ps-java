import java.util.ArrayList;
import java.util.List;

class Solution {

    private static final int ID_START = 1, ID_END = 0;
    private final String[] inputs;
    private int iInput = 0;

    public Solution(String[] inputs) {
        this.inputs = inputs;
    }

    public String solution() {
        var node = Node.nodes[ID_START];

        // 조건을 보면 10번의 차례까지만 윷을 던질 수 있다.
        for (int turn = 0; turn < 10; ++turn) {

            // 문제의 조건을 보면, 윷은 굴리는 순간마다 말을 매번 전진시켜야 한다.
            // while (true) {
            //     var iDice = playables.indexOf(node.distanceToShortcut());
            //     if (iDice == -1) break;
            //     var dice = playables.remove(iDice);
            //     node = node.moveToNextNode(dice);
            // }

            for (var dice : roll()) {
                node = node.moveToNextNode(dice);
            }
            // System.err.println(node.id + " " + node.pos);
        }
        if (node.id == ID_END && node.pos > 0) return "WIN";
        return "LOSE";
    }

    private List<Integer> roll() {
        var playables = new ArrayList<Integer>();
        while (iInput < inputs.length) {
            var dice = toDistance(inputs[iInput++]);
            playables.add(dice);
            if (dice < 4) break;
        }
        return playables;
    }

    private int toDistance(String input) {
        if ("1111".equals(input)) return 5;
        return (int) input.chars().filter(c -> c == '0').count();
    }

    static class Node {
        // 3   2
        //   5  
        // 4   1(0)

        static final int ID_NOT_EXIST = -1;
        static final int[][] dist = new int[5 + 1][5 + 1];
        static final Node[] nodes = new Node[5 + 1];

        static {
            dist[0][0] = Integer.MAX_VALUE;
            dist[1][2] = dist[2][3] = dist[3][4] = dist[4][0] = 5;
            dist[2][5] = dist[3][5] = dist[5][4] = dist[5][0] = 3;

            // 1->2->3->4->0->0
            // 2->5, 3->5, 5->4, 5->0
            nodes[0] = new Node(0, 0, ID_NOT_EXIST);
            nodes[1] = new Node(1, 2, ID_NOT_EXIST);
            nodes[2] = new Node(2, 3, 5);
            nodes[3] = new Node(3, 4, 5);
            nodes[4] = new Node(4, 0, ID_NOT_EXIST);
            nodes[5] = new Node(5, 0, ID_NOT_EXIST);
        }

        // 각각의 노드는 다음 경로로의 정보를 담아야 한다.
        private int pos = 0, id, normalId, shortcutId;

        Node(int id, int normalId, int shortcutId) {
            this.id = id;
            this.normalId = normalId;
            this.shortcutId = shortcutId;
        }

        Node moveToNextNode(int distance) {
            this.pos += distance;
            var nextId = nextNodeId();
            int length = dist[id][nextId];
            if (pos < length) return this;

            var nextNode = nodes[nextId];
            nextNode.pos = pos - length;
            System.err.println(nextNode.id + " " + nextNode.pos);
            return nextNode.moveToNextNode(0);
        }

        int distanceToShortcut() {
            return dist[id][getShortcutId()] - pos;
        }

        private int nextNodeId() {
            return canUseShortcut() ? getShortcutId() : normalId;
        }

        private boolean canUseShortcut() {
            return distanceToShortcut() == 0;
        }

        private int getShortcutId() {
            return shortcutId != ID_NOT_EXIST ? shortcutId : normalId;
        }
    }
}