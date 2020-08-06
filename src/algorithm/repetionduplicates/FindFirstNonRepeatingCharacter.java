package algorithm.repetionduplicates;

public class FindFirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String str = "abcgedabc";
        char c = findFirstNonRepeatingCharacter(str);
        System.out.println(c);
    }

    private static char findFirstNonRepeatingCharacter(String str) {
        int n = str.length();
        char[] arr = str.toCharArray();

        //1st row stores counts
        //2nd row stores last indices
        int[][] count = new int[2][256];

        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            count[0][ch]++;
            count[1][ch] = i;
        }

        int max = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (count[0][i] == 1 && count[1][i] < max) {
                max = count[1][i];
            }
        }
        return arr[max];
    }
}
