package leetcode.allproblems;
//https://leetcode.com/problems/find-the-difference/
public class A0389FindTheDifference {
    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd", "abcde"));
    }

    public static char findTheDifference(String s, String t) {
        s = s + t;
        int c = 0;
        for (char ch : s.toCharArray()) {
            c = c ^ ch;
        }
        return (char) c;
    }
}
