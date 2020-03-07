package company.walmart;

import datastructures.binarytree.BinaryTree;

public class CheckForBalancedTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(1);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.left = new BinaryTree.Node(4);
        root.left.right = new BinaryTree.Node(5);
        root.right.left = new BinaryTree.Node(6);
        root.right.right = new BinaryTree.Node(7);
        root.right.right.right = new BinaryTree.Node(8);
        root.right.right.right.right = new BinaryTree.Node(9);
        root.right.right.right.right.right = new BinaryTree.Node(10);

        CheckForBalancedTree c = new CheckForBalancedTree();
        System.out.println();
        System.out.println(c.isBalanced(tree.root));
    }

    private boolean isBalanced(BinaryTree.Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        if (Math.abs(leftHeight - rightHeight) <= 1
                && isBalanced(node.left)
                && isBalanced(node.right)) {
            return true;
        }
        return false;
    }

    private int height(BinaryTree.Node node) {
        if (node == null) {
            return 0;
        }
        int hLeft = height(node.left) + 1;
        int hRight = height(node.right) + 1;
        return hLeft > hRight ? hLeft : hRight;
    }

}
