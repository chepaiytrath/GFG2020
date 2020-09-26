package algorithm.bitwisemagic;
//https://www.geeksforgeeks.org/find-element-appears-array-every-element-appears-twice/

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumberExtraSpace(arr));
        System.out.println(singleNumberBitwise(arr));

    }

    // O(n) with extra space
    private static int singleNumberExtraSpace(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int val : nums) {
            if (!set.contains(val)) {
                set.add(val);
            } else {
                set.remove(val);
            }
        }
        for (int i : set) {
            return i;
        }
        return -1;
    }

    //O(n) without extra space
    private static int singleNumberBitwise(int[] nums) {
        int a = 0;
        for (int val : nums) {
            a = a ^ val;
        }
        return a;
    }
}
