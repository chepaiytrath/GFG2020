package company.amazon.oa.amcat;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}
        };

        System.out.println(findStepsToTreasure(arr));
    }

    // TYPICAL BFS
    private static int findStepsToTreasure(char[][] arr) {
        int[][] next = new int[][]{
                {-1, 0}, {0, -1}, {1, 0}, {0, 1}
        };

        // SINGLE START POINT
        Element e = new Element(0, 0);

        // BFS QUE
        Queue<Element> que = new LinkedList<>();
        que.add(e);

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        visited[0][0] = true;

        int steps = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Element popped = que.poll();
                int x = popped.i;
                int y = popped.j;

                // ONLY UNVISITED AND NOT EQUAL TO D ELEMENTS ARE ADDED SO ONLY POSSIBILITY IS O OR X
                // IF IT IS X THEN RETURN DISTANCE : STEPS
                if (arr[x][y] == 'X') {
                    return steps;
                }

                for (int ind = 0; ind < 4; ind++) {
                    int newX = x + next[ind][0];
                    int newY = y + next[ind][1];
                    if (isSafe(arr, visited, newX, newY)) {
                        que.add(new Element(x + next[ind][0], y + next[ind][1]));
                        // MARK VISITED WHEN ADDED TO QUE SO THAT SAME ELEMENT DOESN'T GET ADDED AGAIN
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
        return 0;
    }

    private static boolean isSafe(char[][] arr, boolean[][] visited, int i, int j) {
        // WITHIN RANGE, NOT VISITED AND NOT EQUAL TO D
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
