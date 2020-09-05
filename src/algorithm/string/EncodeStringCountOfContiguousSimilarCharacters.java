package algorithm.string;

class EncodeStringCountOfContiguousSimilarCharacters {
    public static void main(String[] args) {
        System.out.println(encode("aaaabbccc"));
    }

    public static String encode(String str) {
        //Your code here

        // aaabbabacc

        char[] arr = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        int n = str.length();
        int left = 0;
        int right = 0;


        // KEEP COMPARING CHAR AT RIGHT WITH CHAR AT LEFT
        while (right < n) {
            // TILL RIGHT CHAR == LEFT CHAR, INCREMENT RIGHT
            if (right < n && arr[right] == arr[left]) {
                right++;
            } else {
                // ONCE RIGHT CHAR != LEFT CHAR, APPEND THE LEFT CHAR AND THE INDEX DIFF TO RESULT

                // LEFT CHAR IS THE CHARACTER WHICH HAS BEEN SAME SINCE LAST ITERATIONS
                sb.append((char) arr[left]);

                // DIFF OF INDEX GIVES THE LENGTH OF SAME CHARACTERS
                sb.append(right - left);
                left = right;
            }
        }
        sb.append((char) arr[left]);
        sb.append(right - left);
        return sb.toString();
    }

}