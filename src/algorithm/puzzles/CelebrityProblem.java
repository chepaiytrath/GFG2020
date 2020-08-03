package algorithm.puzzles;

import java.util.Stack;

public class CelebrityProblem {
    static int arr[][] = new int[][]{
            {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}
    };

    public static void main(String[] args) {
        System.out.println(findCelebWithStack(4));
    }

    private static boolean knows(int a, int b) {
        return arr[a][b] == 1;
    }

    private static int findCelebBasic(int n) {
        int celeb = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celeb, i)) {
                celeb = i;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (celeb != i && (knows(celeb, i) || !knows(i, celeb))) {
                System.out.println("CELEBRITY NOT PRESENT");
                return -1;
            }
        }
        return celeb;
    }

    private static int findCelebWithStack(int n) {
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            st.add(i);
        }

        while(st.size() != 1){
            int one = st.pop();
            int two = st.pop();
            if(knows(one, two)){
                st.push(two);
            }else if(knows(two, one)){
                st.push(one);
            }
        }
        int celeb = st.pop();
        for (int i = 0; i < n; i++) {
            if (celeb != i && (knows(celeb, i) || !knows(i, celeb))) {
                System.out.println("CELEBRITY NOT PRESENT");
                return -1;
            }
        }
        return celeb;
    }
}
