package algorithm.puzzles;

// #REVISIT
public class JosephusProblem {
    public static void main(String[] args) {
        int n = 5, k = 2;
        int pos = findSafePosition(n,k);
        System.out.println(pos);
    }

    private static int findSafePosition(int n, int k) {
        if(n == 1){
            return 0;
        }
        return (findSafePosition(n-1, k) + k) % n;
    }

}
