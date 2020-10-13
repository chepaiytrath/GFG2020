package datastructures.graph;

import java.util.*;

public class A08MiscProblems {

    //https://www.geeksforgeeks.org/stepping-numbers/
    public static void findSteppingNumbers(int start, int end) {
        for (int i = 0; i <= 9; i++) {
            // For 0, just print it if within the range
            // For 1-9, find neighbors and keep adding them to BFS queue
            findSteppingNumbersUtil(i, start, end);
        }
    }

    private static void findSteppingNumbersUtil(int src, int start, int end) {
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        while (!que.isEmpty()) {
            Integer popped = que.poll();
            if (popped >= start && popped <= end) {             // Print if within range
                System.out.print(popped + " ");
            }

            if (popped == 0 || popped > end) {                  // Ignore if popped is 0 or greater than end
                continue;
            }

            int lastDigit = popped % 10;

            int n1 = popped * 10 + (lastDigit - 1);             // Find neighbors
            int n2 = popped * 10 + (lastDigit + 1);

            if (lastDigit == 0) {                               // Special case 0 : Dont add any neighbor to BFS queue
                que.add(n2);
            } else if (lastDigit == 9) {                        // Special case 9 : Add only one before
                que.add(n1);
            } else {
                que.add(n1);                                    // Put neighbors: n1 and n2 into BFS queue
                que.add(n2);
            }
        }
    }

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

    // Normal BFS with BFS queue initialized using rotten oranges.
    // Add valid neighbors of each popped orange to BFS queue and mark it as rotten.
    private int rottenOrangesUtil(int[][] mat) {
        Queue<Element> que = new LinkedList<>();
        int row = mat.length;
        int col = mat[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 2) {                   // Initialize BFS queue with rotten oranges
                    que.add(new Element(i, j));
                }
            }
        }
        que.add(null);                                        // Level break
        int ans = 0;                                          // BFS level
        while (true) {
            Element popped = que.poll();
            if (popped == null) {
                if (que.size() == 0) {
                    break;
                } else {
                    ans++;
                    que.add(null);                            // Insert another break
                }
            } else {
                int pi = popped.i;
                int pj = popped.j;

                // Validity : Within bounds and not empty cell and not already rotten
                if (isValid(pi - 1, pj, mat)) {             //Up
                    mat[pi - 1][pj] = 2;                      //Mark it as rotten and add to queue to do the same for its neighbors
                    que.add(new Element(pi - 1, pj));
                }

                if (isValid(pi + 1, pj, mat)) {             //Down
                    mat[pi + 1][pj] = 2;                      //Mark it as rotten and add to queue to do the same for its neighbors
                    que.add(new Element(pi + 1, pj));
                }

                if (isValid(pi, pj - 1, mat)) {             //Left
                    mat[pi][pj - 1] = 2;                      //Mark it as rotten and add to queue to do the same for its neighbors
                    que.add(new Element(pi, pj - 1));
                }

                if (isValid(pi, pj + 1, mat)) {             //Right
                    mat[pi][pj + 1] = 2;                      //Mark it as rotten and add to queue to do the same for its neighbors
                    que.add(new Element(pi, pj + 1));
                }
            }
        }

        return checkAnyFresh(mat) ? -1 : ans;                 //Check if any orange is still fresh (1)
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
            return false;       // mat[i][j] == 0 : Emptyh cell, mat[i][j] == 2 : Rotten orange already
        }
        return true;
    }

    // LOGIC : BFS WITH QUE INITIALIZED BY 0000
    // WHY BFS: IT IS UNWEIGHTED GRAPH AND WE NEED TO FIND THE SHORTEST DISTANCE BETWEEN TWO VERTICES AND THE ROUTE IS DEFINED BY THE SUCCESSORS
    // FIND SUCCESSORS OF EACH POPPED STRING : HOW? : REPLACE EACH CHARACTER WITH EITHER ONE UP OR ONE DOWN
    // EACH STRING GIVES 8 SUCCESSORS : ADD ALL OF THEM TO QUEUE
    // MAINTAIN VISITED SET OF STRINGS INITIALIZED WITH DEADENDS
    // IF POPPED NODE IS ONE OF THE VISITED(ALSO MAY BE A DEADEND), NO NEED TO PUT ITS SUCCESSORS INTO THE QUEUE
    // MAINTAIN A DEPTH WHICH IS INCREMENTED AT BEGINNING OF EACH LEVEL
    // RETURN DEPTH AS ANSWER
    public static int openLock() {
        String target = "0202";
        String[] deadends = new String[]{
                "0201", "0101", "0102", "1212", "2002"
        };

        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet(Arrays.asList(deadends));
        int dis = -1;
        que.add("0000");
        while (!que.isEmpty()) {
            dis++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String popped = que.poll();
                // IF IT IS TARGET, JUST RETURN THE CURRENT DEPTH
                if (popped.equals(target)) {
                    return dis;
                }
                //CHECKS BOTH VISITED AND DEADENDS
                if (visited.contains(popped)) {
                    continue;
                }
                // ONCE CHECKED FOR TARGET AND IF NOT VISITED, MARK IT VISITED
                visited.add(popped);
                Set<String> nextCombos = findNextCombos(popped);
                que.addAll(nextCombos);
            }
        }
        return -1;
    }

    private static Set<String> findNextCombos(String str) {
        // USE SET TO AVOID ADDING DUPLICATE COMBOS
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            char ithChar = str.charAt(i);

            // USE 9/0 INSTEAD OF '9'/'0'
            // USING '9'/'0' RESULTS IN APPENDING OF ITS ASCII VALUE INTO STRING
            String dec = str.substring(0, i) + (ithChar == '0' ? 9 : (ithChar - '0' - 1)) + str.substring(i + 1);
            String inc = str.substring(0, i) + (ithChar == '9' ? 0 : (ithChar - '0' + 1)) + str.substring(i + 1);
            set.add(inc);
            set.add(dec);
        }
        return set;
    }

    public static void snakesAndLadders() {
        int[] board = new int[30];
        Arrays.fill(board, -1);

        // Define snakes and ladders on board, rest all are -1
        // Ladders
        board[2] = 21;
        board[4] = 7;
        board[10] = 25;
        board[19] = 28;

        // Snakes
        board[26] = 0;
        board[20] = 8;
        board[16] = 3;
        board[18] = 6;

        int res = snakesAndLaddersUtil(board);
        System.out.println("Minimum number of moves to reach end : " + res);
    }

    // BFS with queue initialized using index 0 and keep adding its neighbors (curr + 1 to curr + 6). Dont add already visited ones.
    private static int snakesAndLaddersUtil(int[] board) {
        int n = board.length;
        Queue<Integer> que = new LinkedList<>();            //Contains position indices on board. In case of a snake or ladder, put the resultant index.
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        que.add(0);                                         //Initialize BFS queue with 0
        distance[0] = 0;                                    //Distance of 0 from 0 is 0
        visited[0] = true;                                  //Mark 0 as visited

        while (!que.isEmpty()) {
            Integer popped = que.poll();
            for (int i = popped + 1; i <= popped + 6 && i < n; i++) {           //Check for next 6 positions on board : Dice roll
                int pos = i;
                if (board[pos] != -1) {                                         //Update pos if it is a snake or ladder and points to some other position
                    pos = board[pos];
                }

                if (!visited[pos]) {                                            //Put non visited pos into BFS queue. Update its visited flag and distance values
                    visited[pos] = true;
                    distance[pos] = 1 + distance[popped];
                    que.add(pos);
                }
                if (pos == n - 1) {
                    return distance[pos];
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        boggle();
    }

    public static void boggle() {
        char[][] boggle = new char[][]{{'G', 'I', 'Z'}, {'U', 'E', 'K'}, {'Q', 'S', 'E'}};
        Set<String> dictionary = new HashSet<>();
        dictionary.add("GEEKS");
        dictionary.add("FOR");
        dictionary.add("QUIZ");
        dictionary.add("GO");
        dictionary.add("SEEK");

        boolean[][] visited = new boolean[boggle.length][boggle[0].length];         // Each cell shouldn't be visited more than once
        Set<String> wordsFound = new HashSet<>();                                   // Populate this

        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[0].length; j++) {                            // Consider every character as a starting character and find all words starting with it
                boogleUtil(boggle, i, j, "", visited, dictionary, wordsFound);  // visited will be provided fresh with all false at each iteration because it is backtracked
            }
        }
        System.out.println(wordsFound);
    }

    private static void boogleUtil(char[][] boggle, int i, int j, String str, boolean[][] visited, Set<String> dictionary,
                                   Set<String> wordsFound) {
        visited[i][j] = true;                               // Backtrack it later
        str = str + boggle[i][j];                           //Create the word found till current cell
        if (dictionary.contains(str)) {
            wordsFound.add(str);                            //If current word is in dictionary, add it to response
        }

        int M = boggle.length;
        int N = boggle[0].length;                           // DFS for all 8 neighbors
        for (int row = i - 1; row <= i + 1 && row < M; row++) {             // Nested for loop gives all 8 possible neighbor combinations
            for (int col = j - 1; col <= j + 1 && col < N; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col]) {           // Check if neighbor is within bounds and not visited already
                    boogleUtil(boggle, row, col, str, visited, dictionary, wordsFound);
                }
            }
        }
        visited[i][j] = false;                              // Backtrack when all 8 neighbors are processed
        str = str.substring(0, str.length() - 1);
    }
}