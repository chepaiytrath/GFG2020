package company.google.medium;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println(isValidSudokuWithStringSet(arr));
    }

    private static boolean isValidSudokuWithStringSet(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

    private static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            Set<Character> rSet = new HashSet<>(), cSet = new HashSet<>(), bSet = new HashSet<>();
            for (int j = 0; j < 9; ++j) {
                // bug - 1 => if duplicated, add will return false;
                if (board[i][j] != '.' && !rSet.add(board[i][j])) return false;
                if (board[j][i] != '.' && !cSet.add(board[j][i])) return false;

                // DIFFICULT TO REMEMBER
                int r = i / 3 * 3 + j / 3;
                int c = i % 3 * 3 + j % 3;
                if (board[r][c] != '.' && !bSet.add(board[r][c])) return false;
            }
        }
        return true;
    }
}