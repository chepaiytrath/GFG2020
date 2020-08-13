package algorithm.basictechniques;

import java.util.ArrayList;
import java.util.List;

public class HCFGCD {
    public static void main(String[] args) {
        hcfListOfNumbers();
    }

    public static void hcfTwoNumbers() {
        int a = 4;
        int b = 8;
        System.out.println(hcf(a, b));
    }

    public static void hcfListOfNumbers() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(8);
        list.add(6);
        int res = 0;
        for (int num : list) {
            res = hcf(num, res);
        }
        System.out.println(res);
    }

    // JUST MEMORIZE
    private static int hcf(int a, int b) {
        if (b == 0) {
            return a;
        }
        return hcf(b, a % b);
    }
}
