// import java.util.LinkedList;
// import java.util.List;
// import java.util.Optional;
// import java.util.Scanner;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;

// interface IRunner {
//     void run();
// }

// public class Main {
//     static final Scanner sc = new Scanner(System.in);
//     static final IRunner runner = new Runner(sc);

//     public static void main(String[] args) {
//         runner.run();
//         sc.close();
//     }
// }

// class Runner implements IRunner {
//     final LinkedList<Integer> source;
//     final LinkedList<Integer> temp;
//     int needId = 1;

//     public Runner(Scanner sc) {
//         sc.nextLine(); // Skip the first line
//         String[] parts = sc.nextLine().split(" ");
//         List<Integer> ids = Stream.of(
//                 parts)
//                 .mapToInt(Integer::parseInt)
//                 .boxed()
//                 .collect(Collectors.toList());
//         this.source = new LinkedList<>(ids);
//         this.temp = new LinkedList<>();
//     }

//     @Override
//     public void run() {
//         while (true) {
//             boolean hasSource = source.peek() != null;
//             boolean isTempAlive = Optional.ofNullable(temp.peek())
//                     .map(id -> id.equals(needId))
//                     .orElse(false);
//             if (!hasSource && !isTempAlive) {
//                 break;
//             }
//             consume();
//         }

//         if (source.isEmpty() && temp.isEmpty()) {
//             System.out.println("Nice");
//         } else {
//             System.out.println("Sad");
//         }
//     }

//     private void consume() {
//         if (source.peek() != null && source.peek() == needId) {
//             System.err.println("source -> dest: " + needId);
//             source.poll();
//             needId++;
//         } else if (temp.peek() != null && temp.peek() == needId) {
//             System.err.println("temp -> dest: " + needId);
//             temp.poll();
//             needId++;
//         } else if (!source.isEmpty()) {
//             System.err.println("source -> temp: " + source.peek());
//             temp.push(source.poll());
//         }
//     }
// }
