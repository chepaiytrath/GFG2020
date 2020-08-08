package company.amazon.oa.amcat;

public class AmazonFresh {
    public static void main(String[] args) {
        String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList1 = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList1, shoppingCart1));

        String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
        String[][] codeList2 = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList2, shoppingCart2));

        String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
        String[][] codeList3 = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList3, shoppingCart3));

        String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
        String[][] codeList4 = {{"apple", "apple"}, {"apple", "apple", "banana"}};
        System.out.println(solve3(codeList4, shoppingCart4));

        String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList5 = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList5, shoppingCart5));

        String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList6 = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList6, shoppingCart6));

        String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList7 = {{"anything", "apple"}, {"banana", "anything", "banana"}};
        System.out.println(solve3(codeList7, shoppingCart7));





        /*String[] shoppingCart2 = {"orange", "banana", "grapes", "banana", "apple", "grapes", "banana"};
        String[][] codeList2 = {{"orange", "grapes", "banana"}, {"apple", "anything", "banana"}};

        System.out.println(mine(codeList2, shoppingCart2));*/
    }

    private static int solve(String[][] codeList, String[] shoppingCart) {
        // checking corner cases
        if (codeList == null || codeList.length == 0)
            return 1;
        if (shoppingCart == null || shoppingCart.length == 0)
            return 0;

        int i = 0, j = 0;
        //int codeLen = codeList[i].length;
        while (i < codeList.length && j + codeList[i].length <= shoppingCart.length) {
            boolean match = true;
            for (int k = 0; k < codeList[i].length; k++) {
                if (!codeList[i][k].equals("anything") && !shoppingCart[j + k].equals(codeList[i][k])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                j += codeList[i].length;
                i++;
            } else {
                j++;
            }
        }
        return (i == codeList.length) ? 1 : 0;
    }

    private static int solve2(String[][] codeList, String[] shoppingCart) {
        if (codeList == null || codeList.length == 0)
            return 1;
        if (shoppingCart == null || shoppingCart.length == 0)
            return 0;
        int i = 0, j = 0;
        for (int k = 0; k < shoppingCart.length; k++) {
            if (codeList[i][j].equals(shoppingCart[k]) || codeList[i][j].equals("anything")) {
                j++;
                if (j == codeList[i].length) {
                    i++;
                    j = 0;
                }
                if (i == codeList.length)
                    return 1;
            } else {
                j = codeList[i][0].equals("anything") ? 1 : 0;
            }
        }
        return 0;
    }

    //MY SOLUTION
    private static int solve3(String[][] codeList, String[] shoppingCart) {
        int row = codeList.length;
        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < row && idx <= shoppingCart.length - codeList[i].length) {
            while (j < codeList[i].length) {
                if (codeList[i][j] == "anything" || shoppingCart[idx] == codeList[i][j]) {
                    j++;
                    idx++;
                }else{
                    j = 0;
                    idx++;
                    break;
                }
            }
            if(j == codeList[i].length){
                j = 0;
                i++;
            }
        }
        return i == codeList.length ? 1 : 0;
    }
}
