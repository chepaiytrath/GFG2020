package educative.gtci.p10subsets.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A03Permutations {
    public static void main(String[] args) {
        permutations();
    }

    private static void permutations() {
        int[] arr = new int[]{1, 3, 5};
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> que = new LinkedList<>();
        que.add(new ArrayList<>());

        // Similar to BFS
        // Keep popping lists from queue
        for (int num : arr) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                List<Integer> oldList = que.poll();
                // In each popped list (oldList), insert the current num at each index (0 to oldList.size)
                // Adding num at various indices gives a different permutation
                for (int j = 0; j <= oldList.size(); j++) {
                    List<Integer> newList = new ArrayList<>(oldList);
                    newList.add(j, num);
                    // If by adding the current num the size of the permutation becomes equal to length of arr
                    // ex. for [1,3,5] : Adding 5 at first index to [1,3] makes it [5,1,3] : which is a valid permutation of [1,3,5]
                    if(newList.size() == arr.length){
                        res.add(newList);
                    }
                    // If length of the new list is still less than arr lengfth : ex. [1,3] or [3,5]
                    // which are not a valid permutation of [1,3,5]
                    else{
                        que.add(newList);
                    }
                }
            }
        }

        System.out.println(res);
    }
}
