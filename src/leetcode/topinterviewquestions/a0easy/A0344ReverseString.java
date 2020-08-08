package leetcode.topinterviewquestions.a0easy;

public class A0344ReverseString {

    //Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
    //You may assume all the characters consist of printable ascii characters.
    public static void main(String[] args) {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c);
        }
    }

    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        int lo = 0;
        int hi = s.length - 1;

        while (lo <= hi) {
            swap(s, lo, hi);
            lo++;
            hi--;
        }
    }

    private static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
