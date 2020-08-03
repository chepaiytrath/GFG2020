package algorithm.puzzles;

public class UglyNumbers {
    // https://www.geeksforgeeks.org/ugly-numbers/

    public static void main(String[] args) {
        int n = 150;
        System.out.println(findNthUglyNumberBruteForce(n));
    }

    //
    private static int findNthUglyNumberBruteForce(int n) {
        if (n < 1) {
            return -1;
        }
        int uglyNum = 1;
        int count = 1;
        while (count < n) {
            uglyNum++;
            if (isUgly(uglyNum)) {
                count++;
            }
        }
        return uglyNum;
    }

    private static boolean isUgly(int uglyNum) {
        uglyNum = maxDivide(uglyNum, 2);
        uglyNum = maxDivide(uglyNum, 3);
        uglyNum = maxDivide(uglyNum, 5);
        return uglyNum == 1;
    }

    private static int maxDivide(int uglyNum, int i) {
        while (uglyNum % i == 0) {
            uglyNum = uglyNum / i;
        }
        return uglyNum;
    }
}