package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/reorder-data-in-log-files/solution/
class ReorderDataInLogFiles {
    public static void main(String[] args) {
        String[] logs = new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"};
        reorderLogFiles(logs);
    }

    public static String[] reorderLogFiles(String[] logs) {
        List<String> diglist = new ArrayList<>();
        List<String> letlist = new ArrayList<>();

        for (String s : logs) {
            String[] arr = s.split(" ");
            if (Character.isDigit(arr[1].charAt(0))) {
                diglist.add(s);
            } else {
                letlist.add(s);
            }
        }

        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String let1, String let2) {
                String[] arr1 = let1.split(" ");
                String[] arr2 = let2.split(" ");

                int i = 1;
                int j = 1;
                while (i < arr1.length && j < arr2.length) {
                    if (arr1[i].equals(arr2[j])) {
                        i++;
                        j++;
                    } else {
                        return arr1[i].compareTo(arr2[i]);
                    }
                }
                if (i == arr1.length && j == arr2.length) {
                    return arr1[0].compareTo(arr2[0]);
                } else if (i == arr1.length) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Collections.sort(letlist, myComp);
        letlist.addAll(diglist);

        String[] res = new String[letlist.size()];
        int i = 0;
        for (String s : letlist) {
            res[i++] = s;
        }
        return res;
    }
}