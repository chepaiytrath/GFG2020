package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubtreeWithMaximumAverageOldestTeam {
    static double max = 0.0;
    static TreeNode res = null;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        TreeNode t1 = new TreeNode(12);
        TreeNode t2 = new TreeNode(18);
        TreeNode t3 = new TreeNode(11);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(15);
        TreeNode t7 = new TreeNode(8);

        root.children = new ArrayList<>(Arrays.asList(t1, t2));
        t1.children = new ArrayList<>(Arrays.asList(t3, t4, t5));
        t2.children = new ArrayList<>(Arrays.asList(t6, t7));


        findSubtreeWithMaximumAverageOldestTeam(root);
        System.out.println(max);
        System.out.println(res.data);
    }

    private static Pair findSubtreeWithMaximumAverageOldestTeam(TreeNode node) {
        if (node.children == null || node.children.size() == 0) {
            return new Pair(node.data, 1);
        }

        double currSum = node.data;
        double nodes = 1;
        for (TreeNode child : node.children) {
            Pair temp = findSubtreeWithMaximumAverageOldestTeam(child);
            currSum += temp.totalSum;
            nodes += temp.totalNodes;
        }

        if ((double)currSum / (double)nodes > max) {
            max = currSum / nodes;
            res = node;
        }

        double totalSum = currSum;
        double totalNodes = nodes;
        return new Pair(totalSum, totalNodes);
    }

    static class Pair {
        double totalSum;
        double totalNodes;

        Pair(double totalSum, double totalNodes) {
            this.totalSum = totalSum;
            this.totalNodes = totalNodes;
        }
    }

    static class TreeNode {
        int data;
        List<TreeNode> children;

        TreeNode(int data) {
            this.data = data;
        }
    }
}