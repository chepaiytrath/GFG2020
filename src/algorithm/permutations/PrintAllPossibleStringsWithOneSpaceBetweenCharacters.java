package algorithm.permutations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://practice.geeksforgeeks.org/problems/print-all-possible-strings/1/?track=amazon-strings&batchId=192
class PrintAllPossibleStringsWithOneSpaceBetweenCharacters {
    public static void main(String[] args) {
        printSpace("abcd");
    }

    public static void printSpace(String str) {
        // Your code here
        int n = str.length();
        int i = 0;
        char[] arr = str.toCharArray();
        Queue<List<String>> que = new LinkedList<>();
        List<String> init = new ArrayList<>();
        init.add("" + arr[i]);
        que.add(init);
        i++;

        while (i < n && !que.isEmpty()) {
            char ch = arr[i];
            List<String> popped = que.poll();
            List<String> nl = new ArrayList<>();
            for (String s : popped) {
                nl.add(s + ch);
                nl.add(s + " " + ch);
            }
            que.add(nl);
            i++;
        }

        List<String> res = que.poll();
        for (String s : res) {
            System.out.print(s + "$");
        }
    }
}