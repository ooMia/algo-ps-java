import java.util.Collections;
import java.util.LinkedList;

class Solution {

    public int solution(int N, int[] cur, int[] seek) {
        LinkedList<Student> students = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            students.add(new Student(cur[i], seek[i]));
        }
        Collections.sort(students);
        System.err.println(students);

        int count = N;
        while (!students.isEmpty()) {
            var iter = students.iterator();
            var student = iter.next();
            if (student.isDone()) {
                --count;
                continue;
            }

            var idx = Collections.binarySearch(students, new Student(student.seek, student.have));
            if (idx >= 0) {
                // found exact match
                count -= 2;
                students.remove(idx);
            } else if (-idx - 1 < students.size()) {
                var candidate = students.get(-idx - 1);
                if (candidate.have == student.seek) {
                    var tmp = new Student(student.have, candidate.seek);
                    students.remove(candidate);
                    --count;

                    // put tmp back to list
                    var insertIdx = Collections.binarySearch(students, tmp);
                    if (insertIdx < 0) insertIdx = -insertIdx - 1;
                    students.add(insertIdx, tmp);
                }
            }
            students.remove(student);
        }
        return count;
    }

    class Student implements Comparable<Student> {
        int have, seek;

        Student(int have, int seek) {
            this.have = have;
            this.seek = seek;
        }

        @Override
        public int compareTo(Student o) {
            if (this.have != o.have) return Integer.compare(this.have, o.have);
            return Integer.compare(this.seek, o.seek);
        }

        boolean isDone() {
            return have == seek;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", have, seek);
        }
    }
}