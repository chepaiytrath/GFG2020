package algorithm.basictechniques.arraysumcumulativesum.duplettripletsum;

// #REVISIT
public class A06TripletSumWithOneNumberSumOfOtherTwo {
    public void countTriplets(int[] arr) {
        // SOLUTION:
        // https://www.geeksforgeeks.org/count-triplets-such-that-one-of-the-numbers-can-be-written-as-sum-of-the-other-two/

        // https://www.youtube.com/watch?v=ZD7WxJ3O2XE
        // Uses Combinations from Math
        // HINT Use Combinatorics : nCr = n! / ((n-r)! * r!)

        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] freq = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }

        int ans = 0;
        ans += freq[0] * (freq[0] - 1) * (freq[0] - 2) / 6;

        for (int i = 1; i <= max; i++) {
            ans += freq[i] * (freq[i - 1]) * freq[0] / 2;
        }

        for (int i = 0; 2 * i <= max; i++) {
            ans += freq[i] * (freq[i] - 1) * (freq[2 * 1]) / 2;
        }

        for (int i = 1; i <= max; i++) {
            for (int j = i + 1; i + j <= max; j++) {
                ans += freq[i] * freq[j] * freq[i + j];
            }
        }
        System.out.println("Number of triplets : " + ans);
    }
}
