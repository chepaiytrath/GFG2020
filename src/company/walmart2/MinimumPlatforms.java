package company.walmart2;

import java.util.Arrays;

public class MinimumPlatforms {
    //https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
    // Keep times in 24 hr. format 
    // Sort arrival times and departure time lists separately 
    // Traverse parallelly on both lists starting on arrival index i = 1 and on departure index j = 0 
    // If a train arrives before departure of another train, that means conflict, increment platform count, move i = i+1 
    // If a train departs the arrival of another train, that means previous train left, decrement platform count, move j = j + 1 
    // Maintain max platform count which is result 
    
    public static void main(String[] args) {
        int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
        int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };

        System.out.println(finMinimumPlatforms(arr, dep));
    }

    private static int finMinimumPlatforms(int[] arr, int[] dep) {
        int n = arr.length;

        int platforms = 1;
        int maxPlatforms = 1;

        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 1;
        int j = 0;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else {
                platforms--;
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, platforms);
        }
        return maxPlatforms;
    }
}