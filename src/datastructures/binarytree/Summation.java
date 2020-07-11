package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class Summation {
    static class INTEGER {
        int val;
    }

    public void findRootOfTheTreeWhereChildrenIdSumForEveryNodeIsGiven(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            res += mat[i][0] - mat[i][1];
        }
        System.out.println(res);
    }

    public void findSumOfAllParentNodesHavingGivenChildNode(BinaryTree tree, int x) {
        INTEGER sum = new INTEGER();
        findSumOfAllParentNodesHavingGivenChildNodeUtil(tree.root, x, sum);
        System.out.println(sum.val);
    }

    private void findSumOfAllParentNodesHavingGivenChildNodeUtil(Node node, int x, INTEGER sum) {
        if (node == null || node.isLeaf()) {
            return;
        }

        if ((node.left != null && node.left.data == x) || (node.right != null && node.right.data == x)) {
            sum.val += node.data;
        }
        findSumOfAllParentNodesHavingGivenChildNodeUtil(node.left, x, sum);
        findSumOfAllParentNodesHavingGivenChildNodeUtil(node.right, x, sum);
    }

}