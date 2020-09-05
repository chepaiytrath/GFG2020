package leetcode.allproblems;

import java.util.Stack;

public class A0394DecodeStringParenthesis {
    public static void main(String[] args) {
//        String s = "abc3[a2[c]]xyz";
        String s = "100[leetcode]";
//        String s = "2[abc]3[cd]ef";
        System.out.println(decodeString(s));
    }
    public static String decodeString(String s) {
        char[] arr = s.toCharArray();
        Stack<String> result = new Stack<String>();
        Stack<Integer> count = new Stack<>();
        String res = "";
        int i = 0;
        while(i < arr.length){
            char ch = arr[i];
            if(Character.isDigit(ch)){
                int c = Character.getNumericValue(ch);
                int j = i+1;
                while(Character.isDigit(arr[j])){
                    c = 10 * c + Character.getNumericValue(arr[j]);
                    j++;
                }
                count.push(c);
                i = j;
            }else if(ch == '['){
                result.push(res);
                res="";
                i++;
            }else if(ch == ']'){
                StringBuilder temp = new StringBuilder(result.pop());
                int c = count.pop();
                for(int idx = 0; idx < c; idx++){
                    temp.append(res);
                }
                res = temp.toString();
                i++;
            }else{
                res += ch;
                i++;
            }
        }
        return res;
    }
}