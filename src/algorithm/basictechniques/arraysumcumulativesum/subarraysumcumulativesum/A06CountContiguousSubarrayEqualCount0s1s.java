package algorithm.basictechniques.arraysumcumulativesum.subarraysumcumulativesum;

import java.util.HashMap;
import java.util.Map;

// #REVISIT
//https://www.geeksforgeeks.org/count-subarrays-equal-number-1s-0s/
class A06CountContiguousSubarrayEqualCount0s1s {
    public static void main(String[] args) {

    }

    // MY OWN SOLUTION BASED ON CUMSUM : PASSING ALL TEST CASES ON GFG
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {


        int count = 0;
        int cumsum = 0;

        // MAP OF COUNTS OF SUBARRAYS ENCOUNTERED TILL PREVIOUS ENCOUNTERED CUMSUM
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // OBSERVATION: TWO CASES
            // WHEN FOR THE FIRST TIME, CUMSUM = 0 IS ENCOUNTERED
            // ONLY SUBARRAY WITH EQUAL 0s 1s = SUBARRAY FROM INDEX = 0 TO CURRENT INDEX

            // IF ANY CUMSUM IS AGAIN ENCOUNTERD
            // EX. 101001 : CUMSUM = 0 ENCOUNTERED AGAIN AT I = 5, PREVIOSULY AT I = 3
            // A. 1 SUBARRAY FROM PREVIOUSLY ENCOUNTERED CUMSUM TILL CURRENT INDEX WHICH WILL HAVE EQUAL 0s 1s : ex. 01
            // B. EX. 2 "EQUAL 0,1 COUNT" SUBARRAYS TILL PREVIOUSLY ENCOUNTERED CUMSUM : ex. 1010, 10
            // TOTAL NUMBER OF SUBARRAYS TILL CURRENT INDEX: 101001, 1001  (B + A),      01(A) = 2 + 1 = 3
            // ADD THIS TO COUNT


            cumsum += arr[i] == 0 ? -1 : 1;

            if (!map.containsKey(cumsum)) {
                // ENCOUNTERED CUMSUM 0 FOR FIRST TIME
                if (cumsum == 0) {
                    // THIS WILL CONSTITUTE A VALID SUBARRAY WITH EQUAL 0s 1s
                    // SO INITIALIZE MAP WITH 1 AND INCREMENT COUNT BY 1
                    map.put(cumsum, 1);
                    count += 1;
                } else {
                    // ANY NON ZERO CUMSUM ENCOUNTERED FOR THE FIRST TIME WILL NOT HAVE EQUAL 0s 1s
                    // SO INITIALIZE MAP WITH 0 AND DONT INCREMENT COUNT
                    map.put(cumsum, 0);
                }
            } else {
                // ANY CUMSUM (0 OR NON ZERO) ENCOUNTERED AGAIN
                // ONLY ADDITION INTO SUBARRAY COUNT WILL BE 1 : READ OBSERVATION ABOVE
                map.put(cumsum, map.get(cumsum) + 1);

                // INCREMENT COUNT WITH COUNT OF SUBARRAY FOR CURRENT CUMSUM IN MAP
                count += map.get(cumsum);
            }
        }
        return count;
    }
}
