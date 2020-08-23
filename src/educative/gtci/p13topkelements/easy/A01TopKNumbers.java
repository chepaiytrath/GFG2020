package educative.gtci.p13topkelements.easy;

import java.util.ArrayList;
import java.util.PriorityQueue;


//THUMB RULE
//FOR Kth LARGEST : USE ASCENDING PQ / MIN HEAP : FIRST LARGEST IS AT THE BOTTOM
//FOR Kth SMALLEST : USE DESCENDING PQ / MAX HEAP : FIRST SMALLEST IS AT THE BOTTOM

//POP WHEN SIZE OF PQ IS GREATER THAN K



//Top ‘K’ largest numbers
public class A01TopKNumbers {
    public static void main(String[] args) {
        topKNumbersBetter();
    }

    private static void topKNumbers() {
        /*int[] arr = new int[]{3, 1, 5, 12, 2, 11};
        int k = 3;*/

        int[] arr = new int[]{5, 12, 11, -1, 12};
        int k = 3;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for (int num : arr) {
            pq.add(num);
            // Unnecessary Polls : LogN time taken at each poll to restructure the heap
            // Solve this by filling heap with k elements initially and then
            // adding to heap only when the current num is greater than top of the heap, thereby maintaining the size
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println(new ArrayList<>(pq));
    }

    // NO UNNECESSARY ADDITION POLLING OF ELEMENTS
    private static void topKNumbersBetter() {
        int[] arr = new int[]{3, 1, 5, 12, 2, 11};
        int k = 3;

        /*int[] arr = new int[]{5, 12, 11, -1, 12};
        int k = 3;*/
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        int i = 0;
        while (i < k) {
            pq.add(arr[i]);
            i++;
        }

        while (i < arr.length) {
            int num = arr[i];
            if (num > pq.peek()) {
                // SEQUENCE DOESNT MATTER FOR POLLING AND ADDING
                pq.poll();
                pq.add(num);
            }
            i++;
        }
        System.out.println(new ArrayList<>(pq));
    }
}
