package company.google.oa;

import java.util.Arrays;

public class SortArrayOfVersionNumbers {
    public static void main(String[] args) {
//        String[] arr = new String[]{"1.1.2", "0.9.1", "1.1.0" };
        String[] arr = new String[]{"1.2", "0.8.1", "1.0" };
        sortVersionsUsingComparator(arr);
        for (String s : arr) {
            System.out.println(s + " ");
        }
    }

    // USING COMPARATOR
    private static void sortVersionsUsingComparator(String[] arr) {
        Arrays.sort(arr, (a, b) -> {
            int first = a.length();
            int second = b.length();
            int len = Math.min(first, second);
            int i = 0;
            while (i < len) {
                char ch1 = a.charAt(i);
                char ch2 = b.charAt(i);
                if (!Character.isDigit(ch1)) {
                    i++;
                    continue;
                }
                if (Character.getNumericValue(ch1) == Character.getNumericValue(ch2)) {
                    i++;
                    continue;
                } else {
                    return Character.getNumericValue(ch1) - Character.getNumericValue(ch2);
                }
            }
            return -1;
        });
    }
}
