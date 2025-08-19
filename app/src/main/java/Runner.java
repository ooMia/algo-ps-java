import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final Student[] students;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var N = reader.readInts()[0];
            students = new Student[N];
            for (int i = 0; i < N; i++) {
                var input = reader.line().split(" ");
                var name = input[0];
                var day = Integer.parseInt(input[1]);
                var month = Integer.parseInt(input[2]);
                var year = Integer.parseInt(input[3]);
                students[i] = new Student(name, day, month, year);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(students);
        sb.append(res).append('\n');
    }
}