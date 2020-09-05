package algorithm.patternsearching;

class PatternSearchingNaive {
    public static void main(String[] args) {
        System.out.println(strstr("AAAAAAAAAAAAAAAAAB", "AAAAB"));
    }

    public static int strstr(String s, String x) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = x.toCharArray();

        int i = 0;
        int j = 0;

        int index = -1;
        while (i <= arr1.length - arr2.length) {
            // Match each character with first character of pattern
            if (arr1[i] == arr2[0]) {
                int temp1 = i;
                int temp2 = 0;
                int l = temp1 + arr2.length;

                //For each subsequent character match them to pattern characters
                while (temp1 < l && arr1[temp1] == arr2[temp2]) {
                    temp1++;
                    temp2++;
                }

                //If all characters in pattern have matched, check the index location
                if (temp1 == l) {
                    index = i;
                    break;
                }
            }
            i++;
        }
        return index;
    }
}