package company.google.medium;

public class IncreasingAndDecreasingSequence {
    public static void main(String[] args) {
        findIncreasingAndDecreasingSequence("IIII");

//        System.out.println(findIncreasingAndDecreasingSequence("I")));

//        System.out.println(findIncreasingAndDecreasingSequence("IDID").trim());
//        System.out.println(findIncreasingAndDecreasingSequence("DD").trim());
//        System.out.println(findIncreasingAndDecreasingSequence("II").trim());
//        System.out.println(findIncreasingAndDecreasingSequence("DIDI").trim());
//        System.out.println(findIncreasingAndDecreasingSequence("IIDDD").trim());
//        System.out.println(findIncreasingAndDecreasingSequence("DDIDDIID").trim());
    }

    // Prints the minimum number that can be formed from
    // input sequence of I's and D's
    static void findIncreasingAndDecreasingSequence(String arr) {
        // Initialize current_max (to make sure that
        // we don't use repeated character
        int curr_max = 0;

        // Initialize last_entry (Keeps track for
        // last printed digit)
        int last_entry = 0;

        int j;

        // Iterate over input array
        for (int i = 0; i < arr.length(); i++) {
            // Initialize 'noOfNextD' to get count of
            // next D's available
            int noOfNextD = 0;

            switch (arr.charAt(i)) {
                case 'I':
                    // If letter is 'I'

                    // Calculate number of next consecutive D's
                    // available
                    j = i + 1;
                    while (j < arr.length() && arr.charAt(j) == 'D') {
                        noOfNextD++;
                        j++;
                    }

                    if (i == 0) {
                        curr_max = noOfNextD + 2;

                        // If 'I' is first letter, print incremented
                        // sequence from 1
                        System.out.print(" " + ++last_entry);
                        System.out.print(" " + curr_max);

                        // Set max digit reached
                        last_entry = curr_max;
                    } else {
                        // If not first letter

                        // Get next digit to print
                        curr_max = curr_max + noOfNextD + 1;

                        // Print digit for I
                        last_entry = curr_max;
                        System.out.print(" " + last_entry);
                    }

                    // For all next consecutive 'D' print
                    // decremented sequence
                    for (int k = 0; k < noOfNextD; k++) {
                        System.out.print(" " + --last_entry);
                        i++;
                    }
                    break;

                // If letter is 'D'
                case 'D':
                    if (i == 0) {
                        // If 'D' is first letter in sequence
                        // Find number of Next D's available
                        j = i + 1;
                        while (j < arr.length() && arr.charAt(j) == 'D') {
                            noOfNextD++;
                            j++;
                        }

                        // Calculate first digit to print based on
                        // number of consecutive D's
                        curr_max = noOfNextD + 2;

                        // Print twice for the first time
                        System.out.print(" " + curr_max + " " + (curr_max - 1));

                        // Store last entry
                        last_entry = curr_max - 1;
                    } else {
                        // If current 'D' is not first letter

                        // Decrement last_entry
                        System.out.print(" " + (last_entry - 1));
                        last_entry--;
                    }
                    break;
            }
        }
        System.out.println();
    }

    /*private static String findIncreasingAndDecreasingSequence(String str) {
        if (str == null || str.isEmpty()) {
            return "CAN'T FORM A SEQUENCE";
        }

        char[] arr = str.toCharArray();
        String result = "";

        int initialDs = findDs(arr, -1);
        int temp = initialDs + 1;
        while (temp > 0) {
            result += " " + (temp--);
        }

        int val = initialDs + 1;
        int i = initialDs;

        while (i < arr.length && (i + 1) < arr.length) {
            if (arr[i] == 'I' && arr[i + 1] == 'D') {
                int dnum = findDs(arr, i);
                int nextIIndex = i + dnum + 1;
                val = val + dnum + 1;
                temp = val;
                result += " " + temp;
                while (dnum > 0) {
                    result += " " + --temp;
                    dnum--;
                }
                i = nextIIndex;
            } else if (arr[i] == 'I') {
                result += " " + (++val);
                i++;
            }
        }
        return result;
    }

    private static int findDs(char[] arr, int i) {
        int count = 0;
        while (i + 1 < arr.length && arr[i + 1] == 'D') {
            count++;
            i++;
        }
        return count;
    }*/
}
