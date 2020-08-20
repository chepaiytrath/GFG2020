package algorithm.puzzles;

// https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
public class FloodFillAlgorithm {
    public static void main(String[] args) {
        int[][] screen = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int x = 4, y = 4, newColor = 3;

        int sourceColor = screen[x][y];

        //Color neighboring nodes with same color as source color and which are not already colored as new color
        fill(screen, x, y, sourceColor, newColor);

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }
    }

    private static void fill(int[][] screen, int x, int y, int sourceColor, int newColor) {
        if (x < 0 || y < 0 || x >= screen.length || y >= screen[0].length) {
            return;
        }

        // Don't color the neighbours which are not of same color as source colour or are already colored to new color
        if (screen[x][y] == newColor || screen[x][y] != sourceColor) {
            return;
        }

        screen[x][y] = newColor;

        fill(screen, x, y - 1, sourceColor, newColor);
        fill(screen, x, y + 1, sourceColor, newColor);
        fill(screen, x - 1, y, sourceColor, newColor);
        fill(screen, x + 1, y, sourceColor, newColor);
    }
}
