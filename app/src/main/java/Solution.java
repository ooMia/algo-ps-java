import java.util.Arrays;
import java.util.Calendar;

class Solution {

    public String solution(Student[] students) {
        Arrays.sort(students);
        StringBuilder sb = new StringBuilder();
        sb.append(students[students.length - 1].name).append('\n');
        sb.append(students[0].name);
        return sb.toString();
    }
}

class Student implements Comparable<Student> {
    final String name;
    final Calendar birthDate;

    Student(String name, int day, int month, int year) {
        this.name = name;
        this.birthDate = Calendar.getInstance();
        this.birthDate.set(year, month - 1, day);
    }

    @Override
    public int compareTo(Student other) {
        return this.birthDate.compareTo(other.birthDate);
    }
}
