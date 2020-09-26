package algorithm.basictechniques.mathematical;

import java.util.HashMap;
import java.util.Map;

class CombinationNCR {

    //nCr = n! / ((n-r)! * r!) : DOESNT WORK FOR LARGE VALUES DUE TO OVERFLOW

    // ANOTHER FORMULA:
    //   C(n, k) = C(n-1, k-1) + C(n-1, k)
    //   C(n, 0) = C(n, n) = 1
    public static void main(String[] args) {
        System.out.println(nCrModpRecursive(100, 30));
    }

    // function to find nC
    public static int nCrModpRecursive(int n, int r){
        int mod = 1000000007;
        Map<String, Integer> map = new HashMap<>();
        return method(n, r, map) % mod;
    }

    // TLE BUT SOLUTION CORRECT
    private static int method(int n, int r, Map<String, Integer> map){
        if(n == r || r == 0){
            return 1;
        }
        if(r > n){
            return 0;
        }
        if(map.containsKey(n + "|" + r)){
            return map.get(n + "|" + r);
        }
        int mod = 1000000007;
        int res = (method(n - 1, r - 1, map) % mod) + (method(n - 1, r, map) % mod);
        map.put(n + "|" + r, res);
        return res;
    }


    //DYNAMIC PROGRAMMING
    public static int nCrModpDP(int n, int r){
        int mod = 1000000007;

        int[][] dp = new int[n + 1][r + 1];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(j == 0 || i == j){
                    dp[i][j] = 1;
                }else if(i < j){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i - 1][j - 1] % mod + dp[i - 1][j] % mod;
                }
            }
        }

        return dp[n][r] % mod;
    }
}