import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface ISupplier extends IStructModifier, IStructState {
    QInput next();

    boolean hasNext();
}

interface IConsumer {
    void consume(QInput input) throws IOException;

    void flush() throws IOException;
}

public class Main {
    /**
     * 1번부터 N번까지 N개의 풍선이 원형으로 놓여 있다.
     * i번 풍선의 오른쪽에는 i+1번 풍선이 있고, 왼쪽에는 i-1번 풍선이 있다.
     * 단, 1번 풍선의 왼쪽에 N번 풍선이 있고, N번 풍선의 오른쪽에 1번 풍선이 있다.
     * 
     * 각 풍선 안에는 종이가 하나 들어있고, 종이에는 -N보다 크거나 같고, N보다 작거나 같은 정수가 하나 적혀있다.
     * 
     * 이 풍선들을 다음과 같은 규칙으로 터뜨린다.
     * 우선, 제일 처음에는 1번 풍선을 터뜨린다.
     * 다음에는 풍선 안에 있는 종이를 꺼내어 그 종이에 적혀있는 값만큼 이동하여 다음 풍선을 터뜨린다.
     * 양수가 적혀 있을 경우에는 오른쪽으로, 음수가 적혀 있을 때는 왼쪽으로 이동한다.
     * 이동할 때에는 이미 터진 풍선은 빼고 이동한다.
     * 
     * 예를 들어 다섯 개의 풍선 안에 차례로 3, 2, 1, -3, -1이 적혀 있었다고 하자.
     * 이 경우 3이 적혀 있는 1번 풍선, -3이 적혀 있는 4번 풍선, -1이 적혀 있는 5번 풍선, 1이 적혀 있는 3번 풍선, 2가 적혀
     * 있는 2번 풍선의 순서대로 터지게 된다.
     * 
     * 첫째 줄에 자연수 N(1 ≤ N ≤ 1,000)이 주어진다. 다음 줄에는 차례로 각 풍선 안의 종이에 적혀 있는 수가 주어진다. 종이에
     * 0은 적혀있지 않다.
     * 
     * 첫째 줄에 터진 풍선의 번호를 차례로 나열한다.
     */
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final ISupplier supplier = new Supplier(br);
    static final IConsumer consumer = new Consumer(bw, supplier);

    public static void main(String[] args) {
        try {
            while (supplier.hasNext()) {
                QInput input = supplier.next();
                consumer.consume(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                consumer.flush();
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// ====== Problem Domain ======

interface IStructModifier {
}

interface IStructState {
}

class Supplier implements ISupplier {
    final BufferedReader reader;

    final Deque<QInput> deque = new ArrayDeque<>();

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            reader.readLine(); // 첫 줄은 무시
            List<Integer> parts = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int i = 0; i < parts.size(); i++) {
                QInput input = new QInput();
                input.set(i + 1, parts.get(i));
                deque.add(input);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        System.err.println(
                Stream.of(deque.toArray()).map(o -> "(" + ((QInput) o).id + ")").collect(Collectors.joining(" ")));
        var res = deque.poll();
        if (deque.isEmpty()) {
            return res;
        }

        int move = res.value;
        if (move > 0) {
            move -= 1;
        }
        move %= deque.size();
        while (move != 0) {
            if (move > 0) {
                deque.addLast(deque.pollFirst());
                move--;
            } else {
                deque.addFirst(deque.pollLast());
                move++;
            }
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return !deque.isEmpty();
    }

}

// record pattern
class QInput {
    int id; // 풍선의 번호
    int value; // 풍선 안에 적힌 값

    public void set(int id, int value) {
        this.id = id;
        this.value = value;
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;
    final StringBuilder sb = new StringBuilder();

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }

    @Override
    public void consume(QInput input) throws IOException {
        sb.append(input.id).append(" ");
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
