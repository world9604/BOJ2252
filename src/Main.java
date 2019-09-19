import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    /**
     * @문제 백준 2252번 : https://www.acmicpc.net/problem/2252
     */
    public static void main(String[] args) {
        final int TWO_PEOPLE = 2;

        Scanner sc = new Scanner(System.in);
        int studentNum = sc.nextInt();
        List<Integer>[] students = new ArrayList[studentNum + 1];
        Arrays.fill(students, new ArrayList());

        int[] inDegree = new int[studentNum + 1];

        int compareNum = sc.nextInt();
        for (int i = 0; i < compareNum; i++) {
            int taller = 0;
            int shortter = 0;

            for (int j = 0; j < TWO_PEOPLE; j++) {
                if (j == 0) {
                    shortter = sc.nextInt();
                } else {
                    taller = sc.nextInt();
                }
            }

            inDegree[taller]++;
            students[shortter].add(taller);
        }

        int[] result = new TopologySort().solve(students, inDegree);
        IntStream.of(result).forEach(value -> System.out.printf("%d ", value));
    }
}

class TopologySort {
    int[] solve(List<Integer>[] students, int[] inDegree) {
        int[] result = new int[students.length - 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int i = 0;
        while (!queue.isEmpty()) {
            int curStudent = queue.poll();
            result[i] = curStudent;

            for (int k = 0; k < students[curStudent].size(); k++) {
                int nextStudent = students[curStudent].get(k);
                inDegree[nextStudent]--;

                if (inDegree[nextStudent] == 0) {
                    queue.add(nextStudent);
                }
            }
            i++;
        }

        return result;
    }
}