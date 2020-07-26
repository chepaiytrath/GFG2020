package datastructures.graph;

public class Backtracking {
    public int minDiff = Integer.MAX_VALUE;

    public void tugOfWar(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        int count = 0;
        int currSum = 0;
        int remainingSum = sum;
        int index = 0;
        int n = arr.length;
        int limit = n % 2 == 0 ? (n / 2) : (n - 1) / 2;
        int[] temp = new int[arr.length];
        int[] sol = new int[arr.length];
        tugOfWarUtil(arr, currSum, remainingSum, index, count, limit, temp, sol);

        System.out.println("FIRST SET");
        for (int i = 0; i < arr.length; i++) {
            if (sol[i] == 1) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
        System.out.println("SECOND SET");
        for (int i = 0; i < arr.length; i++) {
            if (sol[i] == 0) {
                System.out.print(arr[i] + " ");
            }
        }

    }

    private void tugOfWarUtil(int[] arr, int currSum, int remainingSum, int index, int elementsSelected, int limit,
            int[] temp, int[] sol) {
        if (index == arr.length - 1) {
            return;
        } else if (elementsSelected == limit) {
            temp[index] = 1;
            int diff = Math.abs(currSum - remainingSum);
            if (diff < minDiff) {
                for (int i = 0; i < arr.length; i++) {
                    sol[i] = temp[i];
                }
                minDiff = diff;
            }
        } else {
            int val = arr[index];
            temp[index] = 1;
            tugOfWarUtil(arr, currSum + val, remainingSum - val, index + 1, elementsSelected + 1, limit, temp, sol);
            temp[index] = 0;
            tugOfWarUtil(arr, currSum, remainingSum - val, index + 1, elementsSelected, limit, temp, sol);
            temp[index] = 0;
        }
    }

    /*
     * public int min_diff; void TOWUtil(int arr[], int n, boolean curr_elements[],
     * int no_of_selected_elements, boolean soln[], int sum, int curr_sum, int
     * curr_position) { if (curr_position == n) return;
     * 
     * if ((n / 2 - no_of_selected_elements) > (n - curr_position)) return;
     * 
     * TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln, sum, curr_sum,
     * curr_position + 1);
     * 
     * no_of_selected_elements++; curr_sum = curr_sum + arr[curr_position];
     * curr_elements[curr_position] = true;
     * 
     * if (no_of_selected_elements == n / 2) { if (Math.abs(sum / 2 - curr_sum) <
     * min_diff) { min_diff = Math.abs(sum / 2 - curr_sum); for (int i = 0; i < n;
     * i++) soln[i] = curr_elements[i]; } } else { TOWUtil(arr, n, curr_elements,
     * no_of_selected_elements, soln, sum, curr_sum, curr_position + 1); }
     * curr_elements[curr_position] = false; } void tugOfWar(int arr[]) { int n =
     * arr.length; boolean[] curr_elements = new boolean[n]; boolean[] soln = new
     * boolean[n]; min_diff = Integer.MAX_VALUE;
     * 
     * int sum = 0; for (int i = 0; i < n; i++) { sum += arr[i]; curr_elements[i] =
     * soln[i] = false; } TOWUtil(arr, n, curr_elements, 0, soln, sum, 0, 0);
     * 
     * System.out.print("The first subset is: "); for (int i = 0; i < n; i++) { if
     * (soln[i] == true) System.out.print(arr[i] + " "); }
     * System.out.print("\nThe second subset is: "); for (int i = 0; i < n; i++) {
     * if (soln[i] == false) System.out.print(arr[i] + " "); } }
     */
}