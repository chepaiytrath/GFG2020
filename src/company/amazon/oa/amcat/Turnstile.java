package company.amazon.oa.amcat;

import java.util.LinkedList;
import java.util.Queue;

// TESTED AT : https://aonecode.com/amazon-online-assessment-turnstile
public class Turnstile {

    //TEST CASES
//    int[] arrTime = new int[]{0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 14, 14, 14, 15, 15, 15, 18, 18, 18, 18, 19, 21, 22, 22, 23, 24, 25, 27, 27, 28, 28, 28, 28, 29, 30, 30, 30, 31, 32, 32, 32, 33, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 38, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 42, 42, 43, 44, 45, 45, 45, 46, 46, 48, 48, 49, 49, 50, 50, 50, 50};
//    int[] direction = new int[]{0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0};

    public static void main(String[] args) {
        int[] arrTime = new int[]{0, 1, 1, 3, 3};
        int[] direction = new int[]{0, 1, 0, 0, 1};
        int numCustomers = 5;


        /*int[] arrTime = new int[]{0, 0, 1,5};
        int[] direction = new int[]{0, 1, 1, 0};
        int numCustomers = 4;*/

        /*int[] arrTime = new int[]{0, 0, 0, 1, 1, 2, 3, 3, 3};
        int[] direction = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 1};
        int numCustomers = 9;*/

        int[] res = amazonTurnstile(numCustomers, arrTime, direction);
        for (int r : res) {
            System.out.print(r + " ");
        }
        /*System.out.println();
        res = amazonTurnstile2(numCustomers, arrTime, direction);
        for (int r : res) {
            System.out.print(r + " ");
        }*/
    }

    // PASSES ALL TEST CASES
    public static int[] amazonTurnstile(int numCustomers, int[] arrTime, int[] direction) {
        int[] result = new int[numCustomers];

        boolean turnstileUsedEvenOnce = false;
        boolean prevDirWasExit = true;

        int globalTimer = 0;
        int i = 0;

        Queue<Integer> entryQue = new LinkedList();
        Queue<Integer> exitQue = new LinkedList();

        while (i < arrTime.length || !entryQue.isEmpty() || !exitQue.isEmpty()) {
            // COLLECT PEOPLE WHO REACH TURNSTILE AT SAME TIME INTO TWO QUEUES DEPENDING ON THEIR DIRECTION.
            // DIR = 0 : ENTER, DIR = 1 : EXIT
            while (i < arrTime.length && globalTimer == arrTime[i]) { //globalTimer == arrTime[i] : SAME TIME FOR CONSECUTIVE PEOPLE
                if (direction[i] == 0) {
                    entryQue.offer(i);
                } else {
                    exitQue.offer(i);
                }
                i++;
            }

            // DONT USE WHILE HERE
            // IF ANY ONE OF THE QUEUES HAS PEOPLE INSIDE IT, THEN POP THEM AND FILL THE RESULT ARRAY
            if (!entryQue.isEmpty() || !exitQue.isEmpty()) {
                if (!turnstileUsedEvenOnce) {
                    // IF TURNSTILE NOT EVEN USED ONCE, THEN PREFERENCE GIVEN TO PERSON TRYING TO EXIT
                    if (!exitQue.isEmpty() || entryQue.isEmpty()) {
                        int exitPerson = exitQue.poll();
                        result[exitPerson] = globalTimer;
                        prevDirWasExit = true;
                    } else {
                        // IF THERE IS NOBODY IN THE EXIT QUEUE, THEN LET THE PEOPLE WAITING IN ENTRYQUEUE COME IN
                        int entryPerson = entryQue.poll();
                        result[entryPerson] = globalTimer;
                        prevDirWasExit = false;
                    }
                    turnstileUsedEvenOnce = true;
                } else {
                    // IF TURNSTILE HAS ALREADY BEEN USED ONCE, DETERMINE WHICH PERSON TO ALLOT TURNSTILE ACCESS BASED ON PREVIOUS USED DIRECTION : prevDirWasExit FLAG
                    if ((prevDirWasExit && !exitQue.isEmpty()) || entryQue.isEmpty()) {
                        int exitPerson = exitQue.poll();
                        result[exitPerson] = globalTimer;
                        prevDirWasExit = true;
                    } else {
                        int entryPerson = entryQue.poll();
                        result[entryPerson] = globalTimer;
                        prevDirWasExit = false;
                    }
                }
            } else {
                // IF BOTH QUEUES ARE EMPTY, RESET USED FLAG
                turnstileUsedEvenOnce = false;
            }
            globalTimer++;
        }
        return result;
    }
}