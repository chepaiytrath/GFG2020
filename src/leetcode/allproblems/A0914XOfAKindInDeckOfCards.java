package leetcode.allproblems;

import java.util.HashMap;
import java.util.Map;

class A0914XOfAKindInDeckOfCards {
    public static void main(String[] args) {

    }


    // LOGIC: HCF OF COUNTS OF ALL NUMBERS IN ARRAY SHOULD BE MORE THAN 1
    // MEANING: IF HCF IS MORE THAN 1 EX. 2 THEN EACH NUMBER CAN BE 'DIVIDED' BY 2
    // IF HCF IS EQUAL TO 1 THEN THERE IS NO NUMBER THAT CAN EXACTLY DIVIDE EACH NUMBER EXCEPT FOR 1 (I.E. GROUPS OF ONLY 1 CAN BE ACHIEVED)
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : deck){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        int res = 0;
        for(int count : map.values()){
            res = hcf(count, res);
        }
        return res > 1;
    }
    
    private int hcf(int a, int b){
        if(b == 0){
            return a;
        }
        return hcf(b, a % b);
    }
}