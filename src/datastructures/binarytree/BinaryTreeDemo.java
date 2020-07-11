package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        LowestCommonAncestor lca = new LowestCommonAncestor();

        BinaryTree tree = new BinaryTree(new Node(8));
        tree.root.left = new Node(3);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(6);
        tree.root.left.right.left = new Node(4);
        tree.root.left.right.right = new Node(7);
        tree.root.right = new Node(10);
        tree.root.right.right = new Node(14);
        tree.root.right.right.left = new Node(13);
        lca.findMaximumDifferenceBetweenNodeAndItsAncestorInBinaryTree(tree);
    }
}
