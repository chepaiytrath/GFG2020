package algorithm.basictechniques.twopointers;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray {
    //remove the duplicates in-place such that each element appear only once and return the new length
    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 1, 1, 1, 2, 2, 4, 4, 5, 6};
        int length = removeDuplicatesWithExtraSpace(nums);
    }

    private static int removeDuplicatesWithExtraSpace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int start = 0;
        set.add(nums[start]);
        int end = 1;
        while (end < nums.length) {
            int numAtEnd = nums[end];
            if (!set.contains(numAtEnd)) {
                set.add(numAtEnd);
                nums[++start] = numAtEnd;
            }
            end++;
        }
        return start + 1;
    }

    private static int removeDuplicates(int[] nums) {
        if (nums == null && nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 1;
        //Sorted array
        //Keep i at the last known unique digit
        //Keep incrementing j, ignore the ones which are same as nums[i]
        //When a new digit is found, replace nums[i+1] with nums[j] and increment i = i + 1
        while (j < nums.length) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }
}
