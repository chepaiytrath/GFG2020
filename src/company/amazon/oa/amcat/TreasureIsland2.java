package company.amazon.oa.amcat;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland2 {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}
        };

        System.out.println(findStepsToTreasure(arr));
    }

    private static int findStepsToTreasure(char[][] arr) {
        int[][] next = new int[][]{
                {-1, 0}, {0, -1}, {1, 0}, {0, 1}
        };

        boolean[][] visited = new boolean[arr.length][arr[0].length];

        Element e = new Element(0, 0);
        Queue<Element> que = new LinkedList<>();
        que.add(e);

        int dis = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Element popped = que.poll();
                int x = popped.i;
                int y = popped.j;
                if (arr[x][y] == 'X') {
                    return dis;
                }

                for (int ind = 0; ind < 4; ind++) {
                    if (isSafe(arr, visited, x + next[ind][0], y + next[ind][1])) {
                        que.add(new Element(x + next[ind][0], y + next[ind][1]));
                    }
                }
            }
            dis++;
        }
        return -1;
    }

    private static boolean isSafe(char[][] arr, boolean[][] visited, int i, int j) {
        if (i >= 0 && i < arr.length && j >= 0 && j < arr[0].length && arr[i][j] != 'D' && visited[i][j] != true) {
            return true;
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
