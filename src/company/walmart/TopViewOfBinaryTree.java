package company.walmart;

import datastructures.binarytree.BinaryTree;

public class TopViewOfBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(1);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.left = new BinaryTree.Node(4);
        root.left.right = new BinaryTree.Node(5);
        root.right.left = new BinaryTree.Node(6);
        root.right.left.right = new BinaryTree.Node(8);
        root.right.left.right.right = new BinaryTree.Node(9);
        root.right.left.right.right.right = new BinaryTree.Node(10);
        root.right.right = new BinaryTree.Node(7);

        //tree.topViewWithRecursion();
    }
}
