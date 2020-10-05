package algorithm.basictechniques.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
class A03CountDistinctElementsInEveryWindowOfSizeK {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 2, 3};
        int k = 4;

        /*int[] arr = new int[]{1, 2, 4, 4};
        int k = 2;*/
        System.out.println(countDistinct(arr, k));
    }

    // Keep a list of countMap's key size for each window of size K
    private static List<Integer> countDistinct(int arr[], int k) {
        int n = arr.length;
        int start = 0;
        int end = 0;
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        while (end < n) {
            // SHRINK THE WINDOW TILL DISTANCE IS GREATER THAN WINDOW LIMIT
            while (end - start + 1 > k) {
                int startNum = arr[start];
                countMap.put(startNum, countMap.get(startNum) - 1);
                if (countMap.get(startNum) == 0) {
                    countMap.remove(startNum);
                }
                start++;
            }
            countMap.put(arr[end], countMap.getOrDefault(arr[end], 0) + 1);

            // ONCE END == K - 1 : THE FIRST TIME, FOR ALL FURTHER ITERATIONS end - start + 1 WILL ALWAYS BE EQUAL TO k
            // I.E. THE WINDOW WILL ALWAYS BE OF SIZE K
            if (end - start + 1 == k) {
                res.add(countMap.size());
            }
            end++;
        }
        return res;
    }

}