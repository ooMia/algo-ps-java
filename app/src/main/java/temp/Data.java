package temp;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Data implements IData, Comparable<Data> {
    final int y, x;
    final Set<Integer> possibles = new HashSet<>();

    private Data(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Data(int y, int x, int[] rows, int[] cols, int[] block) {
        this(y, x);

        int nLimit = rows.length;
        boolean[] used = new boolean[nLimit + 1];
        for (int i = 0; i < nLimit; ++i) {
            used[rows[i]] = true;
            used[cols[i]] = true;
            used[block[i]] = true;
        }
        for (int i = 1; i < used.length; ++i) {
            if (!used[i]) {
                possibles.add(i);
            }
        }
    }

    @Override
    public int priority() {
        return possibles.size();
    }

    @Override
    public int compareTo(Data o) {
        return Integer.compare(this.priority(), o.priority());
    }

    public boolean isRelated(Data other) {
        // Check if the other Data object has the same row or column
        if (this.y == other.y || this.x == other.x) {
            return true;
        }
        // Check if the other Data object is in the same 3x3 block
        return (this.y / 3 == other.y / 3 && this.x / 3 == other.x / 3);
    }

    public List<Data> update(int value, Queue<Data> all) {
        List<Data> affected = relatives(all);
        for (Data data : affected) {
            data.possibles.remove(value);
        }
        return affected;
    }

    public List<Data> relatives(Queue<Data> all) {
        List<Data> related = new ArrayList<>();
        for (Data data : all) {
            if (this.isRelated(data)) {
                related.add(data);
            }
        }
        return related;
    }

    public Data clone() {
        Data copy = new Data(this.y, this.x);
        copy.possibles.addAll(this.possibles);
        return copy;
    }
}

interface IData {
    int priority();

    boolean isRelated(Data other);

    List<Data> update(int value, Queue<Data> all);

    List<Data> relatives(Queue<Data> all);

    Data clone();
}