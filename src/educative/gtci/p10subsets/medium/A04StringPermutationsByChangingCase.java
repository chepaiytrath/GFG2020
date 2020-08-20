package educative.gtci.p10subsets.medium;

import java.util.ArrayList;
import java.util.List;

public class A04StringPermutationsByChangingCase {
    public static void main(String[] args) {
        findPermutations();
    }

    private static void findPermutations() {
        String input = "ab7c";
        int n = input.length();
        List<String> res = new ArrayList<>();
        res.add(input);

        for (int i = 0; i < n; i++) {
            if (Character.isLetter(input.charAt(i))) {
                int size = res.size();
                for (int j = 0; j < size; j++) {
                    String str = res.get(j);
                    char[] arr = str.toCharArray();
                    if (Character.isLowerCase(arr[i])) {
                        arr[i] = Character.toUpperCase(arr[i]);
                    } else {
                        arr[i] = Character.toLowerCase(arr[i]);
                    }
                    res.add(String.valueOf(arr));
                }
            }
        }
        System.out.println(res);
    }


}
