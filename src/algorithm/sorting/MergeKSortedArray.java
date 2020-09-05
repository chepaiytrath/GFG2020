package algorithm.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArray {

    public static void main(String[] args) {
        int[][] arr1 = {
                {1, 3, 5, 7},
                {2, 4, 6, 8},
                {0, 9, 10, 11, 15}
        };
        System.out.println(mergeKSortedArray(arr1).toString());
    }

    private static List<Integer> mergeKSortedArray(int[][] input) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.value - b.value);
        List<Integer> res = new LinkedList<Integer>();
        for (int i = 0; i < input.length; i++) {
            Node n = new Node(input[i][0], i, 0);
            queue.add(n);
        }
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            res.add(n.value);
            // If more elements are present in the same array from which smallest number was fetched, add them
            // Otherwise, in next iteration, the next smallest number will be popped
            if (n.currentIndex < input[n.arrNumber].length - 1) {
                int index = n.currentIndex + 1;
                Node n1 = new Node(input[n.arrNumber][index], n.arrNumber, index);
                queue.add(n1);
            }
            n = null;
        }
        return res;
    }

    static class Node {
        int value;
        int arrNumber;
        int currentIndex;

        public Node(int value, int arrNumber, int currentIndex) {
            this.value = value;
            this.arrNumber = arrNumber;
            this.currentIndex = currentIndex;
        }
    }

}