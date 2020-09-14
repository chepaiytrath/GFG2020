package algorithm.string;

import java.util.*;

public class PermutationsOfString {
    public static void main(String[] args) {
        String s = "ABC";

        System.out.println(permutes(s));
    }

    private static List<String> permutes(String str) {
        if(str.length() == 1){
            List<String> list = new ArrayList<>();
            list.add(str);
            return list;
        }

        Queue<List<String>> que = new LinkedList<>();
        int curr = 0;
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(str.charAt(curr)));
        que.add(list);
        List<String> res = new ArrayList<>();
        while (!que.isEmpty()) {
            List<String> poppedList = que.poll();
            curr++;
            List<String> nextList = new ArrayList<>();
            char ch = str.charAt(curr);
            for (String ele : poppedList) {
                for (int i = 0; i <= ele.length(); i++) {
                    String newstr = ele.substring(0, i) + ch + ele.substring(i);
                    if(newstr.length() == str.length()){
                        res.add(newstr);
                    }else{
                        nextList.add(newstr);
                    }
                }
            }
            if(nextList.size() > 0){
                que.add(nextList);
            }
        }

        Collections.sort(res);
        return res;
    }
}
