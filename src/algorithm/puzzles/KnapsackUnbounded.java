package algorithm.puzzles;

public class KnapsackUnbounded {
    public static void main(String[] args) {
        int sack = 5;
        int[] weights = new int[]{5, 3, 4, 2};
        int[] prices = new int[]{60, 50, 70, 30};

//        int prices[] = new int[]{60, 100, 120};
//        int weights[] = new int[]{10, 20, 30};
//        int sack = 50;

        int maxWtPossible = findMaxWeightPossibleInUnboundedKnapsackWithDynamicProgramming(weights, prices, sack);
        System.out.println(maxWtPossible);
    }

    private static int findMaxWeightPossibleInUnboundedKnapsackWithDynamicProgramming(int[] weights, int[] prices, int sack) {
        return 0;
    }
}