package algorithm.longestmaximumsubstringsubsequence.basic;

class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }

        int start = 0;
        int end = 0;

        int n = s.length();
        char[] arr = s.toCharArray();
        for(int i = 0; i < n; i++){
            int len1 = findPalindromicLength(arr, i, i);
            int len2 = findPalindromicLength(arr, i, i + 1);
            int maxPalLen = Math.max(len1, len2);

            if(maxPalLen > end - start){
                start = i - (maxPalLen - 1) / 2;
                end = i + (maxPalLen) / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int findPalindromicLength(char[] arr, int i, int j){
        while(i >= 0 && j <= arr.length - 1 && arr[i] == arr[j] ){
            i--;
            j++;
        }
        return j - i - 1;
    }
}