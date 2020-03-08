package company.walmart;

import java.util.PriorityQueue;

public class kThLargestNumberInStream {
    static PriorityQueue<Integer> descending = new PriorityQueue<>();

    public static void main(String[] args) {
        int arr[] = {5, 4, 2, 9, 3, 0};
        int k = 3;
        for (int i : arr) {
            descending.add(i);
            if (descending.size() > k) {
                descending.remove();
            }
        }
        System.out.println(descending.remove());
    }
}
