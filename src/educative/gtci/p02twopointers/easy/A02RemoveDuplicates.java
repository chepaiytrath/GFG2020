package educative.gtci.p02twopointers.easy;

// #REVISIT
// #DIFFERENTPATTERN
public class A02RemoveDuplicates {
    public static void main(String[] args) {
        removeAllOccurrencesOfAKey();
    }

    // #REVISIT
    // SORTED ARRAY
    private static void removeDupes() {
        int[] arr = new int[]{2, 3, 3, 3, 6, 9, 9};

        int n = arr.length;
        int left = 0;
        int right = 1;

        while (right < n) {
            if (arr[right] != arr[left]) {
                arr[left + 1] = arr[right];
                left++;
            }
            right++;
        }
        System.out.println(left + 1);
    }

    private static void removeAllOccurrencesOfAKey() {
//        int[] arr = new int[]{3, 2, 3, 6, 3, 10, 9, 3};
//        int key = 3;
        int[] arr = new int[]{2, 11, 2, 2, 1};
        int key = 2;

        int n = arr.length;

        int left = -1;
        int right = 0;

        while (right < n) {
            if (arr[right] != key) {
                arr[left + 1] = arr[right];
                left++;
            }
            right++;
        }
        System.out.println(left + 1);
    }
}
