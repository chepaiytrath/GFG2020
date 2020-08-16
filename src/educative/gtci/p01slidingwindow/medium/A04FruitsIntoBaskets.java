package educative.gtci.p01slidingwindow.medium;

import java.util.HashSet;
import java.util.Set;

public class A04FruitsIntoBaskets {
    public static void main(String[] args) {
        fruitsIntoBaskets();
    }

    private static void fruitsIntoBaskets() {
        char[] arr = {'A', 'B', 'C', 'B', 'B', 'C'};
        int k = 2;

        int len = Integer.MIN_VALUE;
        int n = arr.length;
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<>();
        while (end < n) {
            set.add(arr[end]);
            while (set.size() > k) {
                set.remove(arr[start]);
                start++;
            }
            if (set.size() == k) {
                len = Math.max(len, end + 1 - start);
            }
            end++;
        }

        System.out.println(len);
    }
}
