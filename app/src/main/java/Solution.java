import java.util.Arrays;

class Solution {
    public String solution(KeyBoard[] keyBoards) {
        Arrays.sort(keyBoards);

        var sb = new StringBuilder();
        for (var kb : keyBoards) {
            sb.append(kb.key);
        }
        return sb.toString();
    }

}

class KeyBoard implements Comparable<KeyBoard> {
    final int id, delay;
    final char key;

    KeyBoard(int id, int delay, char key) {
        this.id = id;
        this.delay = delay;
        this.key = key;
    }

    @Override
    public int compareTo(KeyBoard o) {
        if (this.delay == o.delay) {
            return Integer.compare(this.id, o.id);
        }
        return Integer.compare(this.delay, o.delay);
    }
}