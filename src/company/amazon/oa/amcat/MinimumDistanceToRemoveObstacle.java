package company.amazon.oa.amcat;

import java.util.LinkedList;

// Start at top left : 0,0 : Always flat
// Flat areas - 1 : Can only go through these
// Trenches - 0 : Avoid these
// Obstacle - 9 : Destination
// Can move NEWS
// Find shortest distance to reach obstacle
public class MinimumDistanceToRemoveObstacle {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 2, 0, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

//        int dis = findMinimumDis(grid);
//        System.out.println(dis);
    }

    /*private static int findMinimumDis(int[][] grid) {
        Queue<Point> que = new LinkedList<>();
    }*/
}
