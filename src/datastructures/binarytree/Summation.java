package datastructures.binarytree;

import java.util.Stack;

import datastructures.binarytree.BinaryTree.Node;

public class Summation {
    TraversalAndView t = new TraversalAndView();

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

    public void mergeTwoBinaryTreesByDoingNodeSumWithRecursion(BinaryTree tree1, BinaryTree tree2) {
        Node res = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(tree1.root, tree2.root);
        t.levelOrderTraversalWithRecursion(new BinaryTree(res));
    }

    private Node mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 != null ? node1 : node2;
        }
        // To save space, merged value can be put into node1 and the same can be
        // returned
        Node res = new Node(node1.data + node2.data);
        res.left = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(node1.left, node2.left);
        res.right = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(node1.right, node2.right);
        return res;
    }

    static class NODEPAIR {
        NODEPAIR(Node n1, Node n2) {
            this.node1 = n1;
            this.node2 = n2;
        }

        Node node1;
        Node node2;
    }

    public void mergeTwoBinaryTreesByDoingNodeSumWithoutRecursion(BinaryTree tree1, BinaryTree tree2) {
        if(tree1.root == null){
            tree1.root = tree2.root;
        }else{
            NODEPAIR node = new NODEPAIR(tree1.root, tree2.root);
            Stack<NODEPAIR> st = new Stack<>();
            st.add(node);
            while (!st.isEmpty()) {
                node = st.pop();
                Node n1 = node.node1;
                Node n2 = node.node2;
                if (n2 == null) { // Only n2 can be null
                    continue;
                }
                node.node1.data += node.node2.data;
                if (n1.left == null && n2.left != null) {
                    n1.left = n2.left;
                    st.push(new NODEPAIR(n1.left, null));
                } else if (n1.left != null) { // Doesnt matter if n2.left is null or non null
                    st.push(new NODEPAIR(n1.left, n2.left));
                }
                if (n1.right == null && n2.right != null) {
                    n1.right = n2.right;
                    st.push(new NODEPAIR(n1.right, null));
                } else if (n1.right != null) { // Doesnt matter if n2.right is null or non null
                    st.push(new NODEPAIR(n1.right, n2.right));
                }
            }
        }
        t.levelOrderTraversalWithRecursion(new BinaryTree(tree1.root));
    }
}