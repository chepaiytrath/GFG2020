package company.walmart2.easy;

import java.util.Arrays;

public class HighestOccurringDigitInPrimeNumberRange {
    public static void main(String[] args) {
        int l = 1;
        int r = 20;
        boolean prime[] = findPrimeNumbers(l, r);
        int[] count = new int[10];
        for (int num = l; num <= r; num++) {
            if (prime[num]) {
                int p = num;
                while (p > 0) {
                    count[p % 10]++;
                    p = p / 10;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int ind = -1;
        for (int i = 1; i < 10; i++) {
            if (max <= count[i]) {
                ind = i;
                max = count[i];
            }
        }
        System.out.println(ind);
    }

    private static boolean[] findPrimeNumbers(int l, int r) {
        boolean[] prime = new boolean[r + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i * i <= r; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= r; j += i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

}
