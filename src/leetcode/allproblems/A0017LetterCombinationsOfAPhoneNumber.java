package leetcode.allproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class A0017LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        /*letterCombinations("23");
        letterCombinations("456");*/
        letterCombinations("");
    }

    static List<String> res = new ArrayList<>();

    public static void letterCombinations(String digits) {
        if(digits.length() == 0){
            System.out.println();
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");


        List<String> choices = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            choices.add(map.get(c));
        }

        int length = digits.length();
        char[] arr = new char[length];
        dfs(choices, arr, 0, length);

        System.out.println(res);
        res.clear();
    }

    private static void dfs(List<String> choices, char[] arr, int index, int length) {
        if (index == length) {
            res.add(String.valueOf(arr));
            return;
        }

        for (char c : choices.get(index).toCharArray()) {
            arr[index] = c;
            dfs(choices, arr, index + 1, length);
        }
    }
}