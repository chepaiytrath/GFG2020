package company.google.easy;

//https://www.geeksforgeeks.org/meta-strings-check-two-strings-can-become-swap-one-string/
public class MetaStrings {
    public static void main(String[] args) {
//        String input1 = "geeks";
//        String input2 = "keegs";
//        String input1 = "rsting";
//        String input2 = "string";
        String input1 = "Converse";
        String input2 = "Conserve";


        boolean isMeta = checkIfMetaString(input1, input2);
        System.out.println(isMeta);
    }

    private static boolean checkIfMetaString(String input1, String input2) {
        char[] arr1 = input1.toCharArray();
        char[] arr2 = input2.toCharArray();

        int prev = -1;
        int curr = -1;
        int diffCount = 0;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                diffCount++;
                if (diffCount > 2) {
                    return false;
                }
                prev = curr;
                curr = i;
            }
        }
        //diffCount == 2 is needed to check that exactly one swap is done
        return (diffCount == 2) && (arr1[prev] == arr2[curr]) && (arr1[curr] == arr2[prev]);
    }
}
