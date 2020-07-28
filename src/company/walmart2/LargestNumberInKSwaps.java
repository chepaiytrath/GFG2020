package company.walmart2;

public class LargestNumberInKSwaps {
    public static void main(String[] args) {
        String num = "129814999";
        int k = 4;
        System.out.println(findLargestNumberInKSwaps(num, 0, k));
    }

    private static String findLargestNumberInKSwaps(String num, int count, int k) {
        if(count == k){
            return num;
        }
        int max = -1;
        int index = -1;
        for (int i = 0; i < num.length(); i++) {
            int n = Character.getNumericValue(num.charAt(i));
            if (n >= max) {
                max = n;
                index = i;
            }
        }
        StringBuffer sb = new StringBuffer(num);
        char firstChar = num.charAt(0);
        char maxChar = num.charAt(index);
        sb.setCharAt(index, firstChar);
        if(index == 0){
            return maxChar + findLargestNumberInKSwaps(sb.toString().substring(1), count, k);
        }
        return maxChar + findLargestNumberInKSwaps(sb.toString().substring(1), count + 1, k);
    }
}