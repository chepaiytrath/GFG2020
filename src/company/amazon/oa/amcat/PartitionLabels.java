package company.amazon.oa.amcat;
//https://leetcode.com/problems/partition-labels/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabelsOptimizedSolution(str));
    }

    //SELF MADE SOLUTION
    public static List<Integer> partitionLabels(String S) {
        if (S == null || S.isEmpty()) {
            return new ArrayList<>() {{
                add(0);
            }};
        }
        char[] arr = S.toCharArray();
        List<Integer> res = new ArrayList<Integer>();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.computeIfAbsent(arr[i], k -> new ArrayList<Integer>());
            list.add(i);
        }
        int prevUpperLimit = 0;
        int maxUpperLimit = 0;
        for (int i = 0; i < arr.length; i++) {
            char charAtI = arr[i];
            List<Integer> indices = map.get(charAtI);
            int lowerLimit = indices.get(0);
            int upperLimit = indices.get(indices.size() - 1);
            if (lowerLimit <= maxUpperLimit && upperLimit > maxUpperLimit) {
                maxUpperLimit = upperLimit;
            } else if (lowerLimit > maxUpperLimit) {
                res.add(maxUpperLimit - prevUpperLimit + 1);
                prevUpperLimit = lowerLimit;
                maxUpperLimit = upperLimit;
            }
        }
        res.add(maxUpperLimit - prevUpperLimit + 1);
        return res;
    }

    //Better space complexity, Uses array instead of map
    public static List<Integer> partitionLabelsOptimizedSolution(String S) {
        if (S == null || S.isEmpty()) {
            return new ArrayList<>() {{
                add(0);
            }};
        }
        List<Integer> res = new ArrayList<>();
        char[] arr = S.toCharArray();
        int[] lastIndexArr = new int[26];
        for (int i = 0; i < arr.length; i++) {
            char charAtI = arr[i];
            lastIndexArr[charAtI - 'a'] = i; //Index w.r.t a : For a=0, for b=1, for c=2 ...
        }

        int maxUpperLimit = 0;
        int lowerLimit = 0;
        for (int i = 0; i < arr.length; i++) {
            char charAtI = arr[i];
            int lastIndexOfCharAtI = lastIndexArr[charAtI - 'a'];
            maxUpperLimit = Math.max(maxUpperLimit, lastIndexOfCharAtI);
            //Current index is same as the last updated upper limit
            //Any characters encountered before this, if repeated, have all been processed

            // DO THIS AFTER CALCULATING MAXUPPERLIMIT
            if (i == maxUpperLimit) {
                res.add(maxUpperLimit - lowerLimit + 1);
                lowerLimit = i + 1;
            }
        }
        return res;
    }
}
