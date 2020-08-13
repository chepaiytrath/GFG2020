package company.google.oa;

class MinimumDominoRotationForEqualRow {
    public static void main(String[] args) {
//        Gives - 1
//        int[] A = new int[]{3, 5, 1, 2, 3};
//        int[] B = new int[]{3, 6, 3, 3, 4};


        int[] A = new int[]{2, 1, 2, 4, 2, 2};
        int[] B = new int[]{5, 2, 6, 2, 3, 2};
        int rot = minDominoRotations(A, B);
        System.out.println(rot);
    }

    // LOGIC : count frequencies of characters in both arrays
    // Keep a track of frequency of a character if it is same in both arrays
    // The first character with frequency diff : countA[i] + countB[i] - same[i] == A.length will give the answer
    // Answer : Length - Min of count
    // O(n)
    public static int minDominoRotations(int[] A, int[] B) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];

        for (int i = 0; i < A.length; i++) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i]) {
                same[A[i]]++;
            }
        }

        for (int i = 0; i < 7; i++) {
            if (countA[i] + countB[i] - same[i] == A.length) {
                return A.length - Math.max(countA[i], countB[i]);
            }
        }
        return -1;
    }
}