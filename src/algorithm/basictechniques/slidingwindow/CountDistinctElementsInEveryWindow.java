package algorithm.basictechniques.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CountDistinctElementsInEveryWindow {
    public static void main(String[] args) {

    }

    //
    private static List<Integer> countDistinct(int arr[], int n, int k) {
        // code here 
        ArrayList<Integer> res = new ArrayList<>();
        // FREQUENCY MAP : KEY SET SIZE MEANS NUMBER OF DISTINCT ELEMENTS
        Map<Integer, Integer> map = new HashMap<>();

        int start = 0;
        int end = 0;
        while (end <= n) {
            // FOR STARTING K CHARACTERS JUST ADD THEM TO WINDOW
            while (end < k) {
                map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
                end++;
            }

            // IN FIRST ITERATION, END WILL BE AT K (1 INDEXED)
            // BUT 0 TO K - 1 IS ALREADY PROCESSED, SO ADD THE MAP SIZE TO RES
            // IN LATER ITERATIONS, END WILL ALWAYS BE AT 1 + WINDOW LENGTH
            // SO ADD MAP SIZE TO PROCESS PREVIOUS INDICES
            res.add(map.size());


            // DELIBERATE CHECK FOR < N
            if (end < n) {
                // EXPANDING THE WINDOW TO RIGHT
                map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
            }

            // SHRINKING THE WINODW FROM LEFT
            map.put(arr[start], map.get(arr[start]) - 1);
            if (map.get(arr[start]) == 0) {
                map.remove(arr[start]);
            }
            start++;


            end++;
        }
        return res;
    }
}