package company.amazon.oa.amcat;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland2 {
    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'D'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}
        };

        System.out.println(findStepsToTreasure(arr));
    }

    private static int findStepsToTreasure(char[][] arr) {
        int[][] next = new int[][]{
                {-1, 0}, {0, -1}, {1, 0}, {0, 1}
        };

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        // BFS QUE
        Queue<Element> que = new LinkedList<>();

        // MULTIPLE START POINTS BFS
        populateStartPoints(que, arr, visited);

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
                        que.add(new Element(newX, newY));

                        // MARK VISITED WHEN ADDED TO QUE SO THAT SAME ELEMENT DOESN'T GET ADDED AGAIN
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private static void populateStartPoints(Queue<Element> que, char[][] arr, boolean[][] visited) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 'S') {
                    que.add(new Element(i, j));

                    // MARK VISITED WHEN ADDED TO QUE SO THAT SAME ELEMENT DOESN'T GET ADDED AGAIN
                    visited[i][j] = true;
                }
            }
        }
    }

    private static boolean isSafe(char[][] arr, boolean[][] visited, int i, int j) {
        // WITHIN RANGE, NOT VISITED AND NOT EQUAL TO D
        if (i >= 0 && i < arr.length && j >= 0 && j < arr[0].length && visited[i][j] == false && arr[i][j] != 'D') {
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
