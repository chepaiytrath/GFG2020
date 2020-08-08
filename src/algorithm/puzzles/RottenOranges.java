package algorithm.puzzles;
//https://leetcode.com/discuss/interview-question/411357/
//https://leetcode.com/problems/rotting-oranges

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        /*int[][] mat = new int[][]{
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };*/

        int[][] mat = new int[][]{{0}};

        int x = orangesRotting(mat);
        System.out.println(x);
    }

    // the value 0 representing an empty cell;
    // the value 1 representing a fresh orange;
    // the value 2 representing a rotten orange.

    static int[][] mat = new int[][]{
            {0, 0, 1, -1},
            {-1, 1, 0, 0}
    };

    public static int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<Element> que = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    Element e = new Element(i, j);
                    que.add(e);
                }
            }
        }
        int count = 0;
        que.add(null);      // Separator
        while (!que.isEmpty()) {
            Element e = que.poll();
            if (e == null) {
                if (!que.isEmpty()) {
                    count++;
                    que.add(null);
                } else {
                    break;
                }
            } else {
                int x = e.i;
                int y = e.j;
                for (int ind = 0; ind <= 3; ind++) {
                    int neighbourI = x + mat[0][ind];
                    int neighbourJ = y + mat[1][ind];
                    if (isValidNeighbour(grid, neighbourI, neighbourJ)) {
                        grid[neighbourI][neighbourJ] = 2;
                        Element ele = new Element(neighbourI, neighbourJ);
                        que.add(ele);
                    }
                }
            }
        }

        boolean isAnyFresh = checkAnyFresh(grid);
        if (isAnyFresh) {
            return -1;
        }
        return count;
    }

    private static boolean isValidNeighbour(int[][] grid, int neighbourI, int neighbourJ) {
        return neighbourI >= 0 && neighbourI < grid.length && neighbourJ >= 0 && neighbourJ < grid[0].length && grid[neighbourI][neighbourJ] == 1;
    }

    private static boolean checkAnyFresh(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Element {
        int i;
        int j;

        Element(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
