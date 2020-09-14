package algorithm.puzzles;

import java.util.HashMap;
import java.util.Map;

class ClimbingStairs {
    public static void main(String[] args) {

    }

    public int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int prev = 1;
        int curr = 2;
        for (int i = 3; i <= n; i++) {
            int temp = curr;
            curr = curr + prev;
            prev = temp;
        }
        return curr;
    }

    public int climbStairs2(int n) {
        if (n <= 0)
            return 0;
        int list[] = new int[n + 1];
        list[0] = list[1] = 1;
        for (int i = 2; i <= n; i++)
            list[i] = list[i - 1] + list[i - 2];
        return list[n];
    }

    //recursively with memorization
    Map<Integer, Integer> map = new HashMap<>();

    ClimbingStairs() {
        map.put(1, 1);
        map.put(2, 2);
    }

    public int climbStairs3(int n) {
        if (n <= 0)
            return 0;
        if (map.containsKey(n))
            return map.get(n);
        map.put(n, climbStairs3(n - 1) + climbStairs3(n - 2));
        return map.get(n);
    }
}