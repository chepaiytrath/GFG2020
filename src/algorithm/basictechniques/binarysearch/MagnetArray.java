package algorithm.basictechniques.binarysearch;

import java.util.ArrayList;
import java.util.List;

//https://practice.geeksforgeeks.org/problems/magnet-array-problem/0/?company[]=D-E-Shaw&page=1&sortBy=newest&query=company[]D-E-Shawpage1sortBynewest
public class MagnetArray {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 10, 20, 30};
        //int[] arr = new int[]{1,2};
        findPoints(arr);
    }

    private static void findPoints(int[] arr) {
        int n = arr.length;
        int i = 0;
        List<String> equilibriums = new ArrayList<>();

        while (i < n - 1) {
            equilibriums.add(findEquilibrium(arr, arr[i] / 1.0, arr[i + 1] / 1.0)); //Check equilibrium points between each pair of points
            i++;
        }

        System.out.println(equilibriums);
    }

    private static String findEquilibrium(int[] arr, Double i, Double j) {
        while (i < j) {
            Double mid = i + (j - i) / 2;
            double force = findForceApplied(arr, mid);
            if (Math.abs(force) < 0.0000000000001) {    // As per problem statement, to qualify for equilibrium point, the force at a point should be less than this
                return String.format("%.2f", mid);      // Format double with large precision value to precision of 2
            } else if (force > 0) {                     // Force from left is stronger than force from right : So search in right half
                i = mid;
            } else if (force < 0) {
                j = mid;                                // Force from right is stronger than force from left : So search in left half
            }
        }
        return String.format("%.2f", i);
    }

    private static double findForceApplied(int[] arr, Double position) {
        // All magnets apply force on a point. The closer the point is to a magnet the stronger the force from that magnet is.
        // Force from any magnet on a point is : 1/distance between the points
        // Assuming left forces are positive and right forces are negative
        Double finalForce = 0.0;
        for (int i = 0; i < arr.length; i++) {
            int magnetIndex = arr[i];
            finalForce += (1 / (position - magnetIndex));  // If position is after magnetIndex : this gives positive force, otherwise negative force.
            // Add all such forces to finalForce
        }
        return finalForce;
    }
}