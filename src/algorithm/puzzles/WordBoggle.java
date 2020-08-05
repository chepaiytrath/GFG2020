package algorithm.puzzles;

import java.util.HashSet;
import java.util.Set;

public class WordBoggle {
    public static void main(String[] args) {
        boggle();
    }
    private static void boggle() {
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

    private static void boogleUtil(char[][] boggle, int i, int j, String str, boolean[][] visited, Set<String> dictionary,
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
