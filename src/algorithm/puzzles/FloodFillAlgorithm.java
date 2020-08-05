package algorithm.puzzles;

public class FloodFillAlgorithm {
    public static void main(String[] args) {
        int[][] screen = {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int x = 4, y = 4, newColor = 3;

        int prevColor = screen[x][y];
        fill(screen, x, y, prevColor, newColor);

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }
    }

    private static void fill(int[][] screen, int x, int y, int prevColor, int newColor) {
        if (x < 0 || y < 0 || x >= screen.length || y >= screen[0].length) {
            return;
        }

        if (screen[x][y] == newColor || screen[x][y] != prevColor) {
            return;
        }

        screen[x][y] = newColor;

        fill(screen, x, y - 1, prevColor, newColor);
        fill(screen, x, y + 1, prevColor, newColor);
        fill(screen, x - 1, y, prevColor, newColor);
        fill(screen, x + 1, y, prevColor, newColor);
    }
}
