package company.amazon.oa.amcat;

import java.util.LinkedList;

public class Turnstile {

    //TEST CASES
//    int[] arrTime = new int[]{0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 14, 14, 14, 15, 15, 15, 18, 18, 18, 18, 19, 21, 22, 22, 23, 24, 25, 27, 27, 28, 28, 28, 28, 29, 30, 30, 30, 31, 32, 32, 32, 33, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 38, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 42, 42, 43, 44, 45, 45, 45, 46, 46, 48, 48, 49, 49, 50, 50, 50, 50};
//    int[] direction = new int[]{0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0};

    public static void main(String[] args) {
        /*int[] arrTime = new int[]{0, 1, 1, 3, 3};
        int[] direction = new int[]{0, 1, 0, 0, 1};
        int numCustomers = 5;*/


        /*int[] arrTime = new int[]{0, 0, 1,5};
        int[] direction = new int[]{0, 1, 1, 0};
        int numCustomers = 4;*/

        int[] arrTime = new int[]{0, 0, 0, 1, 1, 2, 3, 3, 3};
        int[] direction = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 1};
        int numCustomers = 9;

        int[] res = amazonTurnstile2(numCustomers, arrTime, direction);
        for (int r : res) {
            System.out.print(r + " ");
        }
        /*System.out.println();
        res = amazonTurnstile2(numCustomers, arrTime, direction);
        for (int r : res) {
            System.out.print(r + " ");
        }*/
    }

    private static int[] amazonTurnstile(int numCustomers, int[] arrTime, int[] direction) {
        int[] res = new int[numCustomers];
        int prevdir = 1;
        int time = 0;

        int i = 0;
        int j = 1;
        while (j < numCustomers) {
            if (arrTime[i] == arrTime[j]) {
                //CONFLICT
                if (direction[i] == prevdir) {
                    res[i] = time;
                    arrTime[j]++;
                    time++;
                    i++;
                    j++;
                } else if (direction[j] == prevdir) {
                    res[j] = time;
                    arrTime[i]++;
                    time++;
                    j++;
                }
            } else {
                // NO CONFLICT
                res[i] = time;
                time = arrTime[j];
                prevdir = direction[i];
                i = j;
                j++;
            }
        }
        res[i] = time;
        return res;
    }

    // VERY CLOSE
    public static int[] amazonTurnstile2(int numCustomers, int[] arrTime, int[] direction) {
        int n = arrTime.length;
        int[] res = new int[n];

        LinkedList<Integer> entry = new LinkedList();
        LinkedList<Integer> exit = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (direction[i] == 0) {
                entry.add(i);
            } else {
                exit.add(i);
            }
        }
        int prev = -1;
        int currTime = 0;
        int idx = 0;

        while (!entry.isEmpty() && !exit.isEmpty()) {
            int currEntry = entry.peek();
            int currExit = exit.peek();
            int timeEntry = Math.max(arrTime[currEntry], currTime);
            int timeExit = Math.max(arrTime[currExit], currTime);

            if (timeEntry < timeExit) {
                entry.remove();
                res[currEntry] = timeEntry;
                prev = setPrevious(true, currTime, timeEntry, prev);
                currTime = timeEntry;
            } else if (timeEntry > timeExit) {
                exit.remove();
                currTime = timeExit;
                res[currExit] = timeExit;
                prev = setPrevious(false, currTime, timeExit, prev);
                currTime = timeExit;
            } else if (timeEntry == timeExit) {
                if (prev == -1 || prev == 1) {
                    exit.remove();
                    res[currExit] = timeExit;
                    prev = setPrevious(false, currTime, timeExit, prev);
                    currTime = timeExit;
                } else {
                    entry.remove();
                    res[currEntry] = timeEntry;
                    prev = setPrevious(true, currTime, timeEntry, prev);
                    currTime = timeEntry;
                }
            }
            currTime = currTime + 1;
        }

        while (!entry.isEmpty()) {
            int currEntry = entry.remove();
            currTime = Math.max(currTime, arrTime[currEntry]);
            res[currEntry] = currTime;
            currTime += 1;
        }

        while (!exit.isEmpty()) {
            int currExit = exit.remove();
            currTime = Math.max(currTime, arrTime[currExit]);
            res[currExit] = currTime;
            currTime += 1;
        }

        return res;
    }

    public static int setPrevious(boolean entry, int currTime, int time, int prev) {
        if (time > currTime) {
            prev = -1;
        } else {
            prev = entry ? 0 : 1;
        }
        return prev;
    }
}
