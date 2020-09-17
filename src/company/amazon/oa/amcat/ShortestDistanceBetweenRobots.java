package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestDistanceBetweenRobots {
    public static int shortestDistace(int n, int[] xPos, int[] yPos) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = ((xPos[j] - xPos[i]) * (xPos[j] - xPos[i])) + ((yPos[j] - yPos[i]) * (yPos[j] - yPos[i]));
                if (distance > 0) { // Not considering robos at same point
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }
        return minDistance;
    }

    /*public static void main(String[] args) {
        int n = 6;
        int[] xPositions = new int[]{2, 12, 40, 5, 12, 3};
        int[] yPositions = new int[]{3, 30, 50, 1, 10, 3};
        System.out.println(closetPairOfPoints(n, xPositions, yPositions));
    }

    public static double closetPairOfPoints2(int numRobots, int[] xPositions, int[] yPositions) {
        // SORT BOTH ARRAYS(POINTS) ON BASIS OF X INDEX
        quickSort(xPositions, yPositions, 0, numRobots - 1);
        return closetPairOfPointsUtil(numRobots, xPositions, yPositions, 0, numRobots - 1);
    }

    public static double closetPairOfPointsUtil(int numRobots, int[] xPositions, int[] yPositions, int start, int end) {
        if (start == end)
            return 0.0;
        if (numRobots <= 3) {
            return bruteForce(xPositions, yPositions, start, end);
        }
        int mid = start + (end - start) / 2;
        double d1 = closetPairOfPointsUtil(mid - start + 1, xPositions, yPositions, start, mid);
        double d2 = closetPairOfPointsUtil(end - mid, xPositions, yPositions, mid + 1, end);
        double d = Math.min(d1, d2);
        List<int[]> stripPoints = new ArrayList<>();
        stripPoints(xPositions[mid], yPositions[mid], xPositions, yPositions, start, end, stripPoints, d);
        Collections.sort(stripPoints, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        double minStripDistance = d;
        for (int i = 0; i < stripPoints.size(); i++) {
            for (int j = i + 1; j < stripPoints.size(); j++) {
                if (stripPoints.get(j)[1] - stripPoints.get(i)[1] < minStripDistance) {
                    double stripD = distance(stripPoints.get(i)[0], stripPoints.get(j)[0], stripPoints.get(i)[1],
                            stripPoints.get(i)[1]);
                    if (stripD > 0) {
                        minStripDistance = Math.min(minStripDistance, stripD);
                    }
                }
            }
        }
        return Math.min(d, minStripDistance);
    }

    public static void stripPoints(int midX, int midY, int[] xPositions, int[] yPositions, int start, int end,
                                   List<int[]> stripPoints, double d) {
        for (int i = start; i <= end; i++) {
            if (distance(xPositions[i], midX, yPositions[i], midY) < d)
                stripPoints.add(new int[]{xPositions[i], yPositions[i]});
        }
    }

    public static double bruteForce(int[] xPositions, int[] yPositions, int start, int end) {
        double min = Double.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                double dis = distance(xPositions[i], xPositions[j], yPositions[i], yPositions[j]);
                if (dis != 0.0)
                    min = Math.min(min, dis);
            }
        }
        return min;
    }

    public static double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public static void quickSort(int xPositions[], int yPositions[], int start, int end) {
        if (start >= end)
            return;
        int partition = partition(xPositions, yPositions, start, end);
        quickSort(xPositions, yPositions, start, partition - 1);
        quickSort(xPositions, yPositions, partition + 1, end);
    }

    private static int partition(int xPositions[], int[] yPositions, int start, int end) {
        int pivot = xPositions[end];
        int curr = start, i = start;
        for (curr = start; curr < end; curr++) {
            if (xPositions[curr] <= pivot) {
                swap(xPositions, curr, i);
                swap(yPositions, curr, i);
                i++;
            }
        }
        swap(xPositions, end, i);
        swap(yPositions, end, i);
        return i;
    }

    private static void swap(int[] array, int position1, int position2) {
        int temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }*/


    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[] xPositions = new int[]{2, 12, 40, 5, 12, 3};
        int[] yPositions = new int[]{3, 30, 50, 1, 10, 3};
        System.out.println(closetPairOfPoints(n, xPositions, yPositions));
    }

    public static double closetPairOfPoints(int n, int[] positionX, int[] positionY) {
        // Make pairs of points here

        Point[] xSorted = new Point[n];
        Point[] ySorted = new Point[n];
        for (int i = 0; i < n; i++) {
            xSorted[i] = new Point(positionX[i], positionY[i]);
            ySorted[i] = new Point(positionX[i], positionY[i]);
        }

        Arrays.sort(xSorted, (a, b) -> a.x - b.x);
        Arrays.sort(ySorted, (a, b) -> a.y - b.y);

        return closestUtil(xSorted, ySorted, n, 0, n - 1);
    }

    public static double closestUtil(Point Px[], Point Py[], int n, int start, int end) {
        if (start == end)
            return 0.0;

        if (n <= 3)
            return bruteForce(Px, start, end);

        int mid = start + (end - start) / 2;
        Point midPoint = Px[mid];

        // Divide points in y sorted array around the vertical line.
        Point[] Pyl = new Point[mid];
        Point[] Pyr = new Point[n - mid];
        int l = 0, r = 0;

        for (int i = 0; i < n; i++) {
            if (Py[i].x <= midPoint.x && l < mid)
                Pyl[l++] = Py[i];
            else
                Pyr[r++] = Py[i];
        }

        // Consider the vertical line passing through the middle point
        // calculate the smallest distance dl on left of middle point and dr on right side

        double leftdist = closestUtil(Px, Pyl, mid, start, mid);
        double rightdist = closestUtil(Px, Pyr, n - mid, mid + 1, end);

        double mindist = Math.min(leftdist, rightdist);

        // Build an array strip[] that contains points close to the line passing through the middle point
        Point[] strip = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++)
            if (Math.abs(Py[i].x - midPoint.x) < mindist) {
                strip[j] = Py[i];
                j++;
            }

        // Find the closest points in strip. Return the minimum of distance and closest distance is strip[]
        return stripClosest(strip, j, mindist);
    }

    public static double bruteForce(Point P[], int start, int end) {
        double min = Double.MAX_VALUE;
        for (int i = start; i <= end; i++)
            for (int j = i + 1; j <= end; j++) {
                double dis = dist(P[i], P[j]);
                if (dis != 0.0) {
                    min = Math.min(min, dis);
                }
            }
        return min;
    }

    public static double dist(Point p1, Point p2) {
        return Math.abs((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static double stripClosest(Point strip[], int n, double mindist) {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip[j].y - strip[i].y) < mindist; j++) {
                double dist = dist(strip[i], strip[j]);
                if (dist < mindist)
                    mindist = dist;
            }
        }
        return mindist;
    }

}
