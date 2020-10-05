package algorithm.basictechniques.matrix;

//https://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/
// Inspired from Tushar Roy : https://www.youtube.com/watch?v=vi_1eHCsR9A
public class A04LargestSubsquareSurroundedByXs {
    public static void main(String[] args) {
        /*char[][] arr = new char[][]{
                {'X', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'X'}
        };*/

        /*char[][] arr = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'}
        };*/

        // [5][3] will start with checking 4Xs which is not a square then go to 3Xs which is a valid square
        char[][] arr = new char[][]{
                {'X', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X'}
        };

        System.out.println(findLargestSubsquareSurroundedByXs(arr));
    }

    static class Element {
        int hor;    //Count of Xs horizontally till [i][j]
        int ver;    //Count of Xs vertically till [i][j]
        boolean isO; //Is current element O

        Element(int hor, int ver, boolean isO) {
            this.hor = hor;
            this.ver = ver;
            this.isO = isO;
        }

        public String toString() {
            return "Element[hor = " + hor + ", ver = " + ver + "]";
        }
    }

    private static int findLargestSubsquareSurroundedByXs(char[][] arr) {
        int r = arr.length;
        int c = arr[0].length;

        Element[][] dp = new Element[r][c];

        // For any O in arr insert (0,0) into dp
        // For any X calculate hor and ver from previously filled values

        // First element is either O(0,0) or X(1,1)
        dp[0][0] = arr[0][0] == 'O' ? new Element(0, 0, true) : new Element(1, 1, false);


        boolean isXPresent = false; // If even a single X is present : maxSubsquareSize can be defaulted to 1
        int maxSubsquareSize = 0;

        // Populate dp : first row
        // If X : Horizontal is incremented, Vertical is 1
        for (int i = 1; i < c; i++) {
            if (arr[0][i] == 'O') {
                dp[0][i] = new Element(0, 0, true);
            } else if (arr[0][i] == 'X') {
                if (!isXPresent) {
                    isXPresent = true;
                }
                dp[0][i] = new Element(dp[0][i - 1].hor + 1, 1, false);
            }
        }

        // Populate dp : first column
        // If X : Horizontal is 1, Vertical is incremented
        for (int i = 1; i < r; i++) {
            if (arr[i][0] == 'O') {
                dp[i][0] = new Element(0, 0, true);
            } else if (arr[i][0] == 'X') {
                if (!isXPresent) {
                    isXPresent = true;
                }
                dp[i][0] = new Element(1, dp[i - 1][0].ver + 1, false);
            }
        }

        // Populate dp : rest of the matrix
        // Hor = 1 + hor of left element
        // Ver = 1 + ver of upper element
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (arr[i][j] == 'O') {
                    dp[i][j] = new Element(0, 0, true);
                } else if (arr[i][j] == 'X') {
                    if (!isXPresent) {
                        isXPresent = true;
                    }
                    int hor = dp[i][j - 1].hor + 1;
                    int ver = dp[i - 1][j].ver + 1;
                    dp[i][j] = new Element(hor, ver, false);
                }
            }
        }

        if (isXPresent) {
            maxSubsquareSize = 1;   //Defaulting maxSubsquareSize to 1 if any X is found
        }

        // Start traversing arr from last row last column in reverse
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (dp[i][j].isO) {
                    continue;       //Skip if it is 'O'.\
                }
                int hor = dp[i][j].hor;
                int ver = dp[i][j].ver;
                int min = Math.min(hor, ver);  //Take minimum of two counts to try checking if square exists of that size with that point as bottom right corner

                if (min <= maxSubsquareSize) {
                    continue;   //Skip a subsquare size if it is less than already calculated maxSubsquareSize : Because it is useless
                }

                // This means that min > maxSubsquareSize
                int newSize = maxSubsquareSize;
                for (int count = min; count > maxSubsquareSize; count--) {              // Check for each value from min till maxSubsquareSize and break if found for a min
                    if (j - count + 1 >= 0 && dp[i][j - count + 1].ver >= count         // Check j - min + 1 and i - min + 1 for ArrayIndexOutOfBounds
                            && i - count + 1 >= 0 && dp[i - count + 1][j].hor >= count) {
                        // Explanation of this if block
                        // While being at bottom right corner of a square,
                        // find the bottom left corner : check if its vertical count is >= count
                        // find the top right corner : check if its horizontal count is >= count
                        newSize = count;
                        break;
                    }
                }
                maxSubsquareSize = Math.max(maxSubsquareSize, newSize);
            }
        }
        return maxSubsquareSize;
    }
}