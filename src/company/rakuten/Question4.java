package company.rakuten;

import java.util.Arrays;
class Question4 {
    static void populatePrimeArray(boolean prime[], int SIZE) {
        prime[0] = false;
        prime[1] = false;

        for (int p = 2; p * p <= SIZE; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= SIZE; i += p)
                    prime[i] = false;
            }
        }
    }

    static int maxPrimeSubarray(int arr[], int n) {
        int maxElement = Arrays.stream(arr).max().getAsInt();
        boolean primeArr[] = new boolean[maxElement + 1];
        Arrays.fill(primeArr, true);
        populatePrimeArray(primeArr, maxElement);
        int currMax = 0, maxTillNow = 0;
        for (int i = 0; i < n; i++) {
            if (primeArr[arr[i]] == false)
                currMax = 0;
            else {
                currMax++;
                maxTillNow = Math.max(currMax, maxTillNow);
            }
        }
        return maxTillNow;
    }

    public static void main(String[] args) {
        int arr[] = {6, 2, 3, 4};
        int n = arr.length;
        System.out.print(maxPrimeSubarray(arr, n));
    }
}
