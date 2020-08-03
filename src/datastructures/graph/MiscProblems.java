package datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MiscProblems {

    class Element {
        int i;
        int j;

        Element(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void rottenOranges(int[][] mat) {
        int ans = rottenOrangesUtil(mat);
        if (ans == -1) {
            System.out.println("CANNOT ROT ALL ORANGES");
        } else {
            System.out.println("CAN ROT ALL ORANGES IN " + ans + " MOVES");
        }
    }

    private int rottenOrangesUtil(int[][] mat) {
        Queue<Element> que = new LinkedList<>();
        int row = mat.length;
        int col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 2) {
                    que.add(new Element(i, j));
                }
            }
        }
        que.add(null);
        int ans = 0;
        while (true) {
            Element popped = que.poll();
            if (popped == null) {
                if (que.size() == 0) {
                    break;
                } else {
                    ans++;
                    que.add(null);
                }
            } else {
                int pi = popped.i;
                int pj = popped.j;

                if (isValid(pi - 1, pj, mat)) {
                    que.add(new Element(pi - 1, pj));
                    mat[pi - 1][pj] = 2;
                }

                if (isValid(pi + 1, pj, mat)) {
                    que.add(new Element(pi + 1, pj));
                    mat[pi + 1][pj] = 2;
                }

                if (isValid(pi, pj - 1, mat)) {
                    que.add(new Element(pi, pj - 1));
                    mat[pi][pj - 1] = 2;
                }

                if (isValid(pi, pj + 1, mat)) {
                    que.add(new Element(pi, pj + 1));
                    mat[pi][pj + 1] = 2;
                }
            }
        }

        return checkAnyFresh(mat) ? -1 : ans;
    }

    private boolean checkAnyFresh(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        if (i < 0 || i > row - 1 || j < 0 || j > col - 1 || mat[i][j] == 0 || mat[i][j] == 2) {
            return false;
        }
        return true;
    }

    public void snakesAndLadders() {
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++)
            arr[i] = -1;

        // Ladders
        arr[2] = 21;
        arr[4] = 7;
        arr[10] = 25;
        arr[19] = 28;

        // Snakes
        arr[26] = 0;
        arr[20] = 8;
        arr[16] = 3;
        arr[18] = 6;

        int res = snakesAndLaddersUtil(arr);
        System.out.println("Minimum number of moves to reach end : " + res);
    }

    private int snakesAndLaddersUtil(int[] arr) {
        int n = arr.length;
        Queue<Integer> que = new LinkedList<>();
        que.add(0);

        int[] distance = new int[n];
        distance[0] = 0;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        while (!que.isEmpty()) {
            Integer popped = que.poll();
            for (int i = popped + 1; i <= popped + 6 && i < n; i++) {
                int temp = i;
                if (arr[temp] != -1) {
                    temp = arr[temp];
                }
                if (!visited[temp]) {
                    visited[temp] = true;
                    distance[temp] = distance[popped] + 1;
                    que.add(temp);
                }
                if (temp == n - 1) {
                    return distance[temp];
                }
            }
        }
        return -1;
    }

    public void boggle() {
        char[][] boggle = new char[][] { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' } };
        Set<String> dictionary = new HashSet<>();
        dictionary.add("GEEKS");
        dictionary.add("FOR");
        dictionary.add("QUIZ");
        dictionary.add("GO");
        boolean[][] visited = new boolean[boggle.length][boggle[0].length];
        Set<String> found = new HashSet<>();
        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[0].length; j++) {
                boogleUtil(boggle, i, j, "", visited, dictionary, found);
            }
        }
        System.out.println(found);
    }

    private void boogleUtil(char[][] boggle, int i, int j, String str, boolean[][] visited, Set<String> dictionary,
            Set<String> found) {
        visited[i][j] = true;
        str = str + boggle[i][j];
        if (dictionary.contains(str)) {
            found.add(str);
        }
        int M = boggle.length;
        int N = boggle[0].length;
        for (int row = i - 1; row <= i + 1 && row < M; row++) {
            for (int col = j - 1; col <= j + 1 && col < N; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col]) {
                    boogleUtil(boggle, row, col, str, visited, dictionary, found);
                }
            }
        }
        visited[i][j] = false;
        str = str.substring(0, str.length() - 1);
    }
}