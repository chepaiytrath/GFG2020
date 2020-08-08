package algorithm.basictechniques.mathematical;

import java.util.Arrays;

public class FindPrimeInRange {
    public static void main(String[] args) {
        primesBetweenARange(10, 20);
    }

    private static void primesBetweenARange(int lo, int hi) {
        //false: it is prime
        boolean[] prime = new boolean[hi + 1];

        Arrays.fill(prime, false);
        for (int i = 2; i * i <= hi; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= hi; j += i) {
                    prime[j] = true;
                }
            }
        }
        for (int i = lo; i <= hi; i++) {
            if(!prime[i]){
                System.out.println(i + " " + !prime[i]);
            }
        }
    }
}
