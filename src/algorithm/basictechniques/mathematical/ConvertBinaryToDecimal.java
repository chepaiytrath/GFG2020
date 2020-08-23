package algorithm.basictechniques.mathematical;

public class ConvertBinaryToDecimal {
    public static void main(String[] args) {
        System.out.println(convertBinaryToDecimal("11100"));
    }

    private static int convertBinaryToDecimal(String num) {
        char[] arr = num.toCharArray();
        int ans = 0;
        int n = num.length();
        int len = n - 1;
        for (int i = 0; i < n; i++) {
            int digit = Character.getNumericValue(arr[i]);
            ans += digit * (Math.pow(2, len--));
        }
        return ans;
    }
}
