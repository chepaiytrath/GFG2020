package algorithm.basictechniques.binarysearch;

// #REVISIT : ASKED IN ORACLE ROUND 1
public class BinarySearchInSortedRotatedArrayWithoutDuplicates {
    public static void main(String[] args) {
        /*int[] arr = new int[]{5, 6, 7, 8, 9, 10, 1, 2, 3};
        search(arr, 5);
        search(arr, 6);
        search(arr, 7);
        search(arr, 8);
        search(arr, 9);
        search(arr, 10);
        search(arr, 1);
        search(arr, 2);
        search(arr, 3);*/


        int[] arr = new int[]{8, 9, 10, 1, 2, 3, 5, 6, 7};
        search2(arr, 8);
        search2(arr, 9);
        search2(arr, 10);
        search2(arr, 1);
        search2(arr, 2);
        search2(arr, 3);
        search2(arr, 5);
        search2(arr, 6);
        search2(arr, 7);
    }

    private static void search(int[] arr, int target) {
        int n = arr.length;
        int lo = 0;
        int hi = n - 1;

        int index = -1;

        // <= CHECKS FOR LAST INDEX
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                index = mid;
                break;
            } else if (target >= arr[lo] && target < arr[mid]) {
                // CASE 1. TARGET IS BETWEEN A SORTED LEFT SIDE
                hi = mid - 1;
            }
            // REST OF THE CASES ARE FOR NON SORTED LEFT : I.E. ROTATION EXISTS LHS AND RHS IS SORTED
            // NEED TO FIND THE SITUATION IN WHICH WE STILL NEED TO GO TO LHS INSPITE OF IT BEING NON SORTED

            // NON SORTED WILL MEAN : ARR[LO] > ARR[MID]
            else if (arr[mid] < arr[lo] && target < arr[mid]) {
                // BOTH target < arr[mid] AND target <= arr[mid] WILL WORK FINE FOR ALL CASES
                // REASON : MID NEED NOT BE CONSIDERED BECAUSE IT IS ALREADY CHECKED IN FIRST IF CASE

                // CASE 2. THERE IS A ROTATION AS MID < LO. ALSO TARGET IS LESSER THAN MID
                hi = mid - 1;
            } else if (arr[mid] < arr[lo] && target >= arr[lo]) {
                // ONLY target >= arr[lo] WILL WORK BECAUSE IT IS NOT CHECKED YET LIKE CASE 2

                // CASE 3. THERE IS A ROTATION AS MID < LO. ALSO TARGET IS GREATER THAN LO
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        System.out.println(index);
    }

    // SAME AS ABOVE
    private static void search2(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        int index = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (arr[mid] == target) {
                index = mid;
                break;
            } else if (target >= arr[i] && target < arr[mid]) {
                j = mid - 1;
            } else if (arr[mid] < arr[i] && target >= arr[i]) {
                j = mid - 1;
            } else if (arr[mid] < arr[i] && target < arr[mid]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        System.out.println(index);

        // CANT USE THIS
        /*else if (target >= arr[i] && target > arr[mid]) {
            j = mid - 1;
        } else if (target < arr[mid] && target < arr[i]) {
            j = mid - 1;
        }*/
    }



}
